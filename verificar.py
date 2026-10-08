#!/usr/bin/env python3
# ===========================================================================
#  Verificador del Lab S46 — modelado de ARKA.
#  Carga TU schema.sql y consultas.sql, y revisa 6 cosas.
#  Uso:  python3 verificar.py            (busca schema.sql y consultas.sql aqui)
#  Escribe SQL estilo PostgreSQL; el verificador lo adapta a SQLite para probarlo.
# ===========================================================================
import sqlite3, re, sys, os

def leer(f):
    if not os.path.exists(f):
        print(f"[X] falta {f}")
        sys.exit(1)

    with open(f, encoding="utf-8") as archivo:
        return archivo.read()

def pg_a_sqlite(sql):
    # traducciones minimas Postgres -> SQLite para poder probar el esquema
    sql = re.sub(r'BIGSERIAL\s+PRIMARY\s+KEY', 'INTEGER PRIMARY KEY AUTOINCREMENT', sql, flags=re.I)
    sql = re.sub(r'\bSERIAL\b', 'INTEGER', sql, flags=re.I)
    sql = re.sub(r'now\(\)', 'CURRENT_TIMESTAMP', sql, flags=re.I)
    return sql

PASS, TOTAL = 0, 6
print("== S46: modelado de ARKA (6 comprobaciones) ==")
schema = pg_a_sqlite(leer("schema.sql"))
con = sqlite3.connect(":memory:"); con.execute("PRAGMA foreign_keys=ON"); c = con.cursor()

# (1) el esquema carga
try:
    c.executescript(schema); print("[OK] (1) el esquema carga sin errores"); PASS += 1
except Exception as e:
    print(f"[X] (1) el esquema no carga: {e}"); print(f"PUNTAJE: {PASS}/{TOTAL}"); sys.exit(0)

def tablas(): return [r[0] for r in c.execute("SELECT name FROM sqlite_master WHERE type='table'")]

# (2) solicitud con PK en id
try:
    info = c.execute("PRAGMA table_info(solicitud)").fetchall()
    pk = [r[1] for r in info if r[5] > 0]
    if 'id' in pk: print("[OK] (2) 'solicitud' tiene clave primaria en id"); PASS += 1
    else: print(f"[X] (2) 'solicitud' debe tener PRIMARY KEY en id (PK actual: {pk})")
except Exception as e: print(f"[X] (2) no hay tabla 'solicitud'? {e}")

# (3) indice sobre estado
def indices_de(t):
    cols = set()
    for idx in c.execute(f"PRAGMA index_list({t})").fetchall():
        for r in c.execute(f"PRAGMA index_info({idx[1]})").fetchall(): cols.add(r[2])
    return cols
try:
    if 'estado' in indices_de('solicitud'): print("[OK] (3) hay indice sobre 'estado' (para listar por estado)"); PASS += 1
    else: print("[X] (3) crea un indice sobre solicitud(estado)")
except Exception as e: print(f"[X] (3) {e}")

# (4) evento con FK a solicitud
try:
    fks = c.execute("PRAGMA foreign_key_list(evento)").fetchall()
    if any(f[2] == 'solicitud' for f in fks): print("[OK] (4) 'evento' tiene clave foranea a 'solicitud'"); PASS += 1
    else: print("[X] (4) 'evento.solicitud_id' debe REFERENCES solicitud(id)")
except Exception as e: print(f"[X] (4) no hay tabla 'evento'? {e}")

# (5) indice sobre evento(solicitud_id)
try:
    if 'solicitud_id' in indices_de('evento'): print("[OK] (5) hay indice sobre evento(solicitud_id)"); PASS += 1
    else: print("[X] (5) crea un indice sobre evento(solicitud_id)")
except Exception as e: print(f"[X] (5) {e}")

# (6) datos de prueba + consultas requeridas
try:
    c.executemany("INSERT INTO solicitud(id,tipo,estado) VALUES (?,?,?)",
        [("INC-001","Incidente","BORRADOR"),("CAM-002","Cambio","ENVIADA"),("INC-003","Incidente","ENVIADA")])
    c.execute("INSERT INTO evento(solicitud_id,tipo) VALUES ('INC-001','SolicitudEnviada')")
    con.commit()
    porid = c.execute("SELECT tipo FROM solicitud WHERE id='CAM-002'").fetchone()
    enviadas = [r[0] for r in c.execute("SELECT id FROM solicitud WHERE estado='ENVIADA' ORDER BY id")]
    evs = c.execute("SELECT COUNT(*) FROM evento WHERE solicitud_id='INC-001'").fetchone()[0]
    if porid and porid[0]=="Cambio" and enviadas==["CAM-002","INC-003"] and evs==1:
        print("[OK] (6) las consultas por id, por estado y de eventos devuelven lo esperado"); PASS += 1
    else:
        print(f"[X] (6) consultas no dan lo esperado (porid={porid}, enviadas={enviadas}, eventos={evs})")
except Exception as e:
    print(f"[X] (6) el esquema no soporta las consultas requeridas: {e}")

print(f"\nPUNTAJE: {PASS}/{TOTAL}")
print("Esquema de ARKA modelado por patron de acceso." if PASS==TOTAL else "Aun no. Revisa arriba.")

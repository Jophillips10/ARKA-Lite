-- Esquema de persistencia de ARKA-Lite (servicio de solicitudes).
-- Dialecto: PostgreSQL (la base real del taller S47). Se modela POR PATRÓN DE ACCESO.

CREATE TABLE solicitud (
  id          VARCHAR(20)  PRIMARY KEY,          -- se busca por id  -> CLAVE PRIMARIA
  tipo        VARCHAR(20)  NOT NULL,
  estado      VARCHAR(20)  NOT NULL,
  creada_en   TIMESTAMP    NOT NULL DEFAULT now()
);

-- Se LISTA por estado (p. ej. "todas las ENVIADA") -> índice sobre esa columna.
CREATE INDEX idx_solicitud_estado ON solicitud (estado);

CREATE TABLE evento (
  id            BIGSERIAL   PRIMARY KEY,
  solicitud_id  VARCHAR(20) NOT NULL REFERENCES solicitud(id),   -- CLAVE FORÁNEA
  tipo          VARCHAR(30) NOT NULL,
  ocurrido_en   TIMESTAMP   NOT NULL DEFAULT now()
);

-- Se consultan los eventos DE una solicitud -> índice sobre la clave foránea.
CREATE INDEX idx_evento_solicitud ON evento (solicitud_id);

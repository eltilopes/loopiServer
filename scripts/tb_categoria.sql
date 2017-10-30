
CREATE TABLE tb_categoria
(
  ci_categoria bigint NOT NULL,
  ds_categoria character varying(40) NOT NULL,
  CONSTRAINT uq_ds_categoria UNIQUE (ds_categoria),
  CONSTRAINT pk_categoria PRIMARY KEY (ci_categoria)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_categoria
  OWNER TO postgres;


CREATE SEQUENCE seq_categoria
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 20;
ALTER TABLE seq_categoria
  OWNER TO postgres;

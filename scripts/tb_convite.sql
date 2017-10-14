-- Table: tb_convite

-- DROP TABLE tb_convite;

CREATE TABLE tb_convite
(
  ci_convite bigint NOT NULL,
  ds_email character varying(60) NOT NULL,
  nr_cpf character varying(14),
  nm_convite character varying(256),
  ds_chave character varying(256),
  CONSTRAINT pk_convite PRIMARY KEY (ci_convite)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_convite
  OWNER TO postgres;

  

CREATE SEQUENCE seq_convite
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 20;
ALTER TABLE seq_convite
  OWNER TO postgres;


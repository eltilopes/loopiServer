-- Table: tb_localizacao

-- DROP TABLE tb_localizacao;

CREATE TABLE tb_localizacao
(
  ci_localizacao bigint NOT NULL,
  nr_latitude double precision NOT NULL,
  nr_longitude double precision NOT NULL,
  cd_profissional bigint NOT NULL,
  dt_localizacao date ,
  CONSTRAINT pk_localizacao PRIMARY KEY (ci_localizacao),
  CONSTRAINT fk_localizacao_profissional FOREIGN KEY (cd_profissional)
      REFERENCES tb_profissional (ci_profissional) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_localizacao
  OWNER TO postgres;


CREATE SEQUENCE seq_localizacao
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 20;
ALTER TABLE seq_localizacao
  OWNER TO postgres;
  
  

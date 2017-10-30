
CREATE SEQUENCE seq_servico_profissional
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 20;
ALTER TABLE seq_servico_profissional
  OWNER TO postgres;

CREATE TABLE tb_servico_profissional
(
  ci_servico_profissional bigint NOT NULL,
  nm_servico_profissional character varying(256) NOT NULL,
  ds_servico_profissional character varying(256) NOT NULL,
  nr_tempo integer NOT NULL,
  nr_valor double precision NOT NULL,
  cd_especialidade bigint NOT NULL,
  cd_profissional bigint NOT NULL,
  CONSTRAINT pk_servico_profissional PRIMARY KEY (ci_servico_profissional),
  CONSTRAINT uq_servico_profissional UNIQUE (nm_servico_profissional,cd_profissional,cd_especialidade ),
  CONSTRAINT fk_servico_profissional_especialidade FOREIGN KEY (cd_especialidade)
      REFERENCES tb_especialidade (ci_especialidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_servico_profissional_profissional FOREIGN KEY (cd_profissional)
      REFERENCES tb_profissional (ci_profissional) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_servico_profissional
  OWNER TO postgres;

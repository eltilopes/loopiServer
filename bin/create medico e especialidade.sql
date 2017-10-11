

CREATE TABLE tb_especialidade
(
  ci_especialidade bigint NOT NULL,
  nm_especialidade character varying(40) NOT NULL,
  CONSTRAINT pk_especialidade PRIMARY KEY (ci_especialidade)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_especialidade
  OWNER TO postgres;

-- Table: tb_medico

-- DROP TABLE tb_medico;

CREATE TABLE tb_medico
(
  ci_medico bigint NOT NULL,
  ds_email character varying(60) NOT NULL,
  ds_crm character varying(10) NOT NULL,
  nr_cpf character varying(14),
  nm_medico character varying(60),
  CONSTRAINT pk_medico PRIMARY KEY (ci_medico)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_medico
  OWNER TO postgres;


CREATE TABLE tb_especialidade_medico
(
  cd_medico bigint NOT NULL,
  cd_especialidade bigint NOT NULL,
  CONSTRAINT fk_especialidade_medico FOREIGN KEY (cd_especialidade)
      REFERENCES tb_especialidade (ci_especialidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE,
  CONSTRAINT fk_medico_especialidade_medico FOREIGN KEY (cd_medico)
      REFERENCES tb_medico (ci_medico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_usuario_role
  OWNER TO postgres;

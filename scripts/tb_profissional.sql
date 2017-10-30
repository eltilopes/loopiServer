-- Table: tb_profissional

-- DROP TABLE tb_profissional;

CREATE TABLE tb_profissional
(
  ci_profissional bigint NOT NULL,
  cd_usuario bigint NOT NULL,
  cd_categoria bigint NOT NULL,
  cd_sub_categoria bigint NOT NULL,
  cd_especialidade bigint NOT NULL,
  CONSTRAINT pk_profissional PRIMARY KEY (ci_profissional),
  CONSTRAINT fk_categoria_profissional FOREIGN KEY (cd_categoria)
      REFERENCES tb_categoria (ci_categoria) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_especialidade_profissional FOREIGN KEY (cd_especialidade)
      REFERENCES tb_especialidade (ci_especialidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_profissional_sub_categoria FOREIGN KEY (cd_sub_categoria)
      REFERENCES tb_sub_categoria (ci_sub_categoria) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_profissional_usuario FOREIGN KEY (cd_usuario)
      REFERENCES tb_usuario_mobile (ci_usuario_mobile) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uq_profissional UNIQUE (cd_usuario, cd_categoria, cd_sub_categoria, cd_especialidade)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_profissional
  OWNER TO postgres;


-- Sequence: seq_profissional

-- DROP SEQUENCE seq_profissional;

CREATE SEQUENCE seq_profissional
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 20;
ALTER TABLE seq_profissional
  OWNER TO postgres;

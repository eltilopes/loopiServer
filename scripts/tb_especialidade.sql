

CREATE TABLE tb_especialidade
(
  ci_especialidade bigint NOT NULL,
  ds_especialidade character varying(40) NOT NULL,
  cd_sub_categoria bigint NOT NULL,
  CONSTRAINT pk_especialidade PRIMARY KEY (ci_especialidade),
    CONSTRAINT fk_sub_categoria_especialidade FOREIGN KEY (cd_sub_categoria)
      REFERENCES tb_sub_categoria (ci_sub_categoria) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_especialidade
  OWNER TO postgres;


CREATE SEQUENCE seq_especialidade
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 20;
ALTER TABLE seq_especialidade
  OWNER TO postgres;

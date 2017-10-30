
CREATE TABLE tb_sub_categoria
(
  ci_sub_categoria bigint NOT NULL,
  ds_sub_categoria character varying(40) NOT NULL,
  cd_categoria bigint NOT NULL,
  CONSTRAINT pk_sub_categoria PRIMARY KEY (ci_sub_categoria),
    CONSTRAINT fk_categoria_sub_categoria FOREIGN KEY (cd_categoria)
      REFERENCES tb_categoria (ci_categoria) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_sub_categoria
  OWNER TO postgres;


CREATE SEQUENCE seq_sub_categoria
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 20;
ALTER TABLE seq_sub_categoria
  OWNER TO postgres;

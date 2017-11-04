-- Table: tb_convite

-- DROP TABLE tb_convite;

CREATE TABLE tb_convite
(
  ci_convite bigint NOT NULL,
  ds_email character varying(60) NOT NULL,
  nr_cpf character varying(14) NOT NULL,
  nm_convite character varying(256) NOT NULL,
  nr_codigo_convite character varying(8) NOT NULL,
  cd_usuario_mobile bigint NOT NULL,
  fl_status bigint NOT NULL,
  dt_criacao date,
  nr_telefone character varying(11) NOT NULL,
  CONSTRAINT pk_convite PRIMARY KEY (ci_convite),
  CONSTRAINT fk_usuario_mobile_convite FOREIGN KEY (cd_usuario_mobile)
      REFERENCES tb_usuario_mobile (ci_usuario_mobile) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uq_usuario_email_cpf_convite UNIQUE (cd_usuario_mobile, ds_email, nr_cpf)
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
  
  

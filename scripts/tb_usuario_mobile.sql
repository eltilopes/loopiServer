-- Table: tb_usuario_mobile

-- DROP TABLE tb_usuario_mobile;

CREATE TABLE tb_usuario_mobile
(
  ci_usuario_mobile bigint NOT NULL,
  ds_email character varying(60) NOT NULL,
  ds_senha character varying(60) NOT NULL,
  fl_ativo integer NOT NULL DEFAULT 1,
  update_lenght integer NOT NULL DEFAULT 1,
  nr_cpf character varying(14),
  ds_nome character varying(60),
  nr_codigo_convite character varying(8),
  CONSTRAINT pk_usuario_mobile PRIMARY KEY (ci_usuario_mobile)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_usuario_mobile
  OWNER TO postgres;



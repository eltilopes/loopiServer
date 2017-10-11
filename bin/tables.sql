create table tb_mobile_version
(
  ci_mobile_version        number not null,
  ds_version_code          number not null,
  ds_version_name          varchar2(60) not null,
  fl_ativo                 number(1) DEFAULT 1 NOT NULL,
  dt_create                date default sysdate not null
);

alter table tb_mobile_version
  add constraint pk_mobile_versionprimary primary key (ci_mobile_version);
  
ALTER TABLE tb_mobile_version ADD FL_MANDATORY NUMBER(1) DEFAULT 0 NOT NULL;
  
  
create sequence seq_mobile_version
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

--insert into tb_mobile_version (ci_mobile_version,ds_version_code,ds_version_name,fl_ativo,dt_create ) values (seq_mobile_version.nextval, 2, '1.1', 1, sysdate)


-- tablela de usuarios --

create table tb_usuario_mobile
(
  ci_usuario_mobile number not null,
  ds_email          varchar2(60) not null,
  ds_senha          varchar2(60) not null,
  fl_ativo          number(1) DEFAULT 1 NOT NULL,
  cd_funcionario    number
);

alter table tb_usuario_mobile
  add constraint pk_usuario_mobile primary key (ci_usuario_mobile);
  
alter table tb_usuario_mobile add constraint uk_usuario_funcionario unique (cd_funcionario);
  
alter table tb_usuario_mobile
  add constraint fk_usuario_funcionario foreign key (cd_funcionario)
  references rh.tb_funcionario (ci_funcionario);

create sequence seq_usuario_mobile
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

---------------------

-- api key --

create table tb_api_key
(
  ci_api_key number not null,
  ds_hash    varchar2(260) not null
);
-- create/recreate primary, unique and foreign key constraints 
alter table tb_api_key add constraint pk_api_key primary key (ci_api_key) ;

alter table tb_api_key add constraint uk_api_key unique (ds_hash);

-------------------------

-- usuario api key

-- create table
create table tb_usuario_mobile_api_key
(
  cd_usuario_mobile number not null,
  cd_api_key        number not null
);
-- create/recreate primary, unique and foreign key constraints 
alter table tb_usuario_mobile_api_key
  add constraint fk_api_key_user foreign key (cd_api_key)
  references tb_api_key (ci_api_key);
alter table tb_usuario_mobile_api_key
  add constraint fk_user_api_key foreign key (cd_usuario_mobile)
  references tb_usuario_mobile (ci_usuario_mobile);

------------------------------
-- tb usuario mobile aux --
create table tb_usuario_mobile_aux
(
  ci_usuario_mobile_aux number not null,
  ds_email              varchar2(60) not null,
  ds_senha              varchar2(60) not null,
  ds_chave              varchar2(200) not null,
  dt_cadastro           date default sysdate not null,
  fl_ativo              number(1) default 1 not null,
  cd_usuario_mobile     number not null
);
-- create/recreate primary, unique and foreign key constraints 
alter table tb_usuario_mobile_aux
  add constraint pk_usuario_mobile_aux primary key (ci_usuario_mobile_aux);
alter table tb_usuario_mobile_aux
  add constraint cd_usuario_mobile foreign key (cd_usuario_mobile)
  references tb_usuario_mobile (ci_usuario_mobile);
  
create sequence seq_tb_usuario_mobile_aux
  minvalue 1
  maxvalue 9999999999999999999999999999
  start with 1
  increment by 1
  cache 20;
  
---------------------------
-- mobile message --
create table tb_mobile_message
(
  ci_mobile_message number not null,
  ds_titulo         varchar2(255) not null,
  ds_corpo          varchar2(1000) not null,
  dt_criacao        date
);
-- create/recreate primary, unique and foreign key constraints 
alter table tb_mobile_message
  add constraint pk_mobile_message primary key (ci_mobile_message);

------------------------------
-- questao de segurança --

create table tb_questao_seguranca
(
  ci_questao_seguranca        number not null,
  ds_questao                  varchar2(500) not null,
  ds_query_resposta           varchar2(1000) not null
);
alter table tb_questao_seguranca
          add constraint pk_questao_seguranca primary key (ci_questao_seguranca);
  
create sequence seq_tb_questao_seguranca
  minvalue 1
  maxvalue 9999999999999999999999999999
  start with 1
  increment by 1
  cache 20;

insert into tb_questao_seguranca 
(ci_questao_seguranca , ds_questao, ds_query_resposta) 
values 
(seq_tb_questao_seguranca.NEXTVAL, 'Qual o primeiro nome da mãe?',
     'select ROWNUM ID,  description, valid from (
     select REGEXP_SUBSTR(f.nm_mae, ''[A-Z]+'') description, 1 valid from rh.tb_funcionario f where f.ci_funcionario = {value}
     union
     select * from(
       select REGEXP_SUBSTR(f.nm_mae, ''[A-Z]+'') description, 0 valid from rh.tb_funcionario f where f.ci_funcionario <> {value}
       group by REGEXP_SUBSTR(f.nm_mae, ''[A-Z]+'')
       order by dbms_random.random
     )error where rownum <4
   ) resp' )
               
           
insert into tb_questao_seguranca 
(ci_questao_seguranca , ds_questao, ds_query_resposta) 
values 
(seq_tb_questao_seguranca.NEXTVAL, 'Qual o último nome da mãe?',
    'select ROWNUM ID,  description, valid from (
     select REGEXP_SUBSTR(f.nm_mae, ''([A-Z]+)$'') description, 1 valid from rh.tb_funcionario f where f.ci_funcionario = {value}
     union
     select * from(
       select REGEXP_SUBSTR(f.nm_mae, ''([A-Z]+)$'') description, 0 valid from rh.tb_funcionario f where f.ci_funcionario <> {value}
       group by REGEXP_SUBSTR(f.nm_mae, ''([A-Z]+)$'')
       order by dbms_random.random
     )error where rownum <4
   ) resp' )
               
insert into tb_questao_seguranca 
(ci_questao_seguranca , ds_questao, ds_query_resposta) 
values 
(seq_tb_questao_seguranca.NEXTVAL, 'Qual o primeiro nome do pai?',
 'select ROWNUM ID,  description, valid from (
     select REGEXP_SUBSTR(f.nm_pai, ''[A-Z]+'') description, 1 valid from rh.tb_funcionario f where f.ci_funcionario = {value}
     union
     select * from(
       select REGEXP_SUBSTR(f.nm_pai, ''[A-Z]+'') description, 0 valid from rh.tb_funcionario f where f.ci_funcionario <> {value}
       group by REGEXP_SUBSTR(f.nm_pai, ''[A-Z]+'')
       order by dbms_random.random
     )error where rownum <4
   ) resp')
                             
               
insert into tb_questao_seguranca 
(ci_questao_seguranca , ds_questao, ds_query_resposta) 
values 
(seq_tb_questao_seguranca.NEXTVAL, 'Qual o último nome do pai?',
   'select ROWNUM ID,  description, valid from (
     select REGEXP_SUBSTR(f.nm_pai, ''([A-Z]+)$'') description, 1 valid from rh.tb_funcionario f where f.ci_funcionario = {value}
     union
     select * from(
       select REGEXP_SUBSTR(f.nm_pai, ''([A-Z]+)$'') description, 0 valid from rh.tb_funcionario f where f.ci_funcionario <> {value}
       group by REGEXP_SUBSTR(f.nm_pai, ''([A-Z]+)$'')
       order by dbms_random.random
     )error where rownum <4
   ) resp')
               
          
                
insert into tb_questao_seguranca 
(ci_questao_seguranca , ds_questao, ds_query_resposta) 
values 
(seq_tb_questao_seguranca.NEXTVAL, 'Qual o dia de nascimento?',
 'select ROWNUM ID,  description, valid from (
     select REGEXP_SUBSTR(f.dt_nascimento, ''(\d+)'') description, 1 valid from rh.tb_funcionario f where f.ci_funcionario = {value}
     union
     select * from(
       select REGEXP_SUBSTR(f.dt_nascimento, ''(\d+)'') description, 0 valid from rh.tb_funcionario f where f.ci_funcionario <> {value}
       group by REGEXP_SUBSTR(f.dt_nascimento, ''(\d+)'')
       order by dbms_random.random
     )error where rownum <4
   ) resp')
           
insert into tb_questao_seguranca
 (ci_questao_seguranca, ds_questao, ds_query_resposta)
values
 (seq_tb_questao_seguranca.NEXTVAL,
  'Qual o mês de nascimento?',
  'select ROWNUM ID,  description, valid from (
     select REGEXP_SUBSTR(f.dt_nascimento,  ''\d{2}'',2) description, 1 valid from rh.tb_funcionario f where f.ci_funcionario = {value}
     union
     select * from(
       select REGEXP_SUBSTR(f.dt_nascimento,  ''\d{2}'',2) description, 0 valid from rh.tb_funcionario f where f.ci_funcionario <> {value}
       group by REGEXP_SUBSTR(f.dt_nascimento,  ''\d{2}'',2)
       order by dbms_random.random
     )error where rownum <4
   ) resp')
------------------------
-- role --
create table tb_role
(
  ci_role number not null,
  nm_role varchar2(40) not null
);
-- create/recreate primary, unique and foreign key constraints 
alter table tb_role
  add constraint pk_role primary key (ci_role);

----------------------
-- usuario role --
create table tb_usuario_role
(
  cd_usuario_mobile number not null,
  cd_role           number not null
);
-- create/recreate primary, unique and foreign key constraints 
alter table tb_usuario_role
  add constraint fk_role_usuario_mobile foreign key (cd_role)
  references tb_role (ci_role)
  disable;
alter table tb_usuario_role
  add constraint fk_usuario_mobile_role foreign key (cd_usuario_mobile)
  references tb_usuario_mobile (ci_usuario_mobile)
  disable;

--------------------------------
-- usuario mobile message --

create table tb_usuario_mobile_message
(
  cd_usuario_mobile         number not null,
  cd_mobile_message         number not null,
  fl_status                 number not null,
  ci_usuario_mobile_message number not null,
   CONSTRAINT uq_usuario_mobile_message UNIQUE (cd_usuario_mobile, cd_mobile_message)
);
-- create/recreate primary, unique and foreign key constraints 
alter table tb_usuario_mobile_message
  add constraint pk_usuario_mobile_message primary key (ci_usuario_mobile_message);
  
alter table tb_usuario_mobile_message
  add constraint fk_mobile_usuario_message foreign key (cd_mobile_message)
  references tb_mobile_message (ci_mobile_message);
alter table tb_usuario_mobile_message
  add constraint fk_usuario_mobile_message foreign key (cd_usuario_mobile)
  references tb_usuario_mobile (ci_usuario_mobile);
-----------------------------



-- select * from oauth_client_details
create table oauth_client_details (
  client_id varchar2(256),
  resource_ids varchar2(256),
  client_secret varchar2(256),
  scope varchar2(256),
  authorized_grant_types varchar2(256),
  web_server_redirect_uri varchar2(256),
  authorities varchar2(256),
  access_token_validity number,
  refresh_token_validity number,
  additional_information varchar2(1000),
  autoapprove varchar2(256)
);

alter table oauth_client_details
  add constraint pk_oauth_client_detais primary key (client_id);
  
-------------
-- select * from oauth_client_token
create table oauth_client_token (
  token_id varchar2(256),
  token blob,
  authentication_id varchar2(256),
  user_name varchar2(256),
  client_id varchar2(256)
);

alter table oauth_client_token
  add constraint pk_oauth_client_token primary key (authentication_id);
  
-------------
-- select * from oauth_access_token
-- delete from oauth_access_token

create table oauth_access_token (
  token_id varchar2(256),
  token blob,
  authentication_id varchar2(256),
  user_name varchar2(256),
  client_id varchar2(256),
  authentication long raw,
  refresh_token varchar2(256)
);

alter table oauth_access_token
  add constraint pk_oauth_access_token primary key (authentication_id);
  
----------------------
-- select * from oauth_refresh_token
-- delete from oauth_refresh_token
create table oauth_refresh_token (
  token_id varchar2(256),
  token blob,
  authentication blob
);

-- select * from oauth_code
create table oauth_code (
  code varchar2(256), authentication blob
);

-- select * from oauth_approvals
create table oauth_approvals (
  userid varchar2(256),
  clientid varchar2(256),
  scope varchar2(256),
  status varchar2(10),
  expiresat timestamp,
  lastmodifiedat timestamp
);


-- customized oauth_client_details table

create table clientdetails (
  appid varchar2(256),
  resourceids varchar2(256),
  appsecret varchar2(256),
  scope varchar2(256),
  granttypes varchar2(256),
  redirecturl varchar2(256),
  authorities varchar2(256),
  access_token_validity number,
  refresh_token_validity number,
  additionalinformation varchar2(1000),
  autoapprovescopes varchar2(256)
);

alter table clientdetails
  add constraint pk_clientdetails primary key (appid);
 
insert into tb_role values (1, 'chamado_read');
insert into tb_role values (2, 'documento_read');
insert into tb_role values (3, 'chamado_create');
insert into tb_role values (4, 'usuario_update');
insert into tb_role values (5, 'usuario_read');
insert into tb_role values (6, 'frequencia_read');
insert into tb_role values (7, 'lotacao_read');
insert into tb_role values (8, 'contra_cheque_read');
insert into tb_role values (9, 'mensagem_create');
insert into tb_role values (10, 'usuario_apikey');
insert into tb_role values (11, 'mensagem_read');
insert into tb_role values (12, 'webmail_read');
insert into tb_role values (13, 'licenca_read');
insert into tb_role values (14, 'send_email_create');


--ALTER TABLE tb_usuario_mobile ADD FL_ATIVO NUMBER(1) DEFAULT 1 NOT NULL;

                  
CREATE TABLE TB_MOBILE_NOTIFICATION_GROUP (
  CI_MOBILE_NOTIFICATION_GROUP NUMBER(19) NOT NULL,
  DT_CRIACAO TIMESTAMP(6),
  NM_GROUP VARCHAR2(100 CHAR) NOT NULL,
  QTDUSERS NUMBER(19)
)
alter table TB_MOBILE_NOTIFICATION_GROUP
  add constraint PK_MOBILE_NOTIFICATION_GROUP primary key (CI_MOBILE_NOTIFICATION_GROUP);
  
ALTER TABLE tb_mobile_notification_group ADD FL_ATIVO NUMBER(1) DEFAULT 1 NOT NULL;

ALTER TABLE tb_usuario_mobile ADD UPDATE_LENGHT NUMBER(1) DEFAULT 1 NOT NULL;
ALTER TABLE tb_usuario_mobile MODIFY UPDATE_LENGHT DEFAULT 0;
  

                  
CREATE TABLE MESSAGE_HAS_NOTIFICATION_GROUP (
  ci_mobile_message NUMBER(19) NOT NULL,
  ci_mobile_notification_group NUMBER(19) NOT NULL,
   CONSTRAINT FK_MOBILE_MESSAGE FOREIGN KEY (ci_mobile_message) REFERENCES tb_mobile_message (ci_mobile_message),
   CONSTRAINT FK_NOTIFICATION_GROUP FOREIGN KEY (ci_mobile_notification_group) REFERENCES tb_mobile_notification_group (ci_mobile_notification_group)
)
                 
CREATE TABLE NOTIFICATION_GROUP_HAS_USUARIO (
  CI_MOBILE_NOTIFICATION_GROUP NUMBER(19) NOT NULL,
  CI_USUARIO_MOBILE NUMBER(19) NOT NULL,
   CONSTRAINT FK_NOTIFICATION_GROUP_USUARIO FOREIGN KEY  (CI_MOBILE_NOTIFICATION_GROUP) REFERENCES TB_MOBILE_NOTIFICATION_GROUP (CI_MOBILE_NOTIFICATION_GROUP),
   CONSTRAINT FK_USUARIO_MOBILE FOREIGN KEY (CI_USUARIO_MOBILE) REFERENCES tb_usuario_mobile (CI_USUARIO_MOBILE)
)

-- sequences --
create sequence seq_tb_api_key
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;
---
create sequence seq_mobile_message
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;
---
create sequence seq_usuario_mobile_message
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;
---
create sequence seq_mobile_notification_group
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

------------------------- GRANT UTIL --------------------------
GRANT SELECT ON TB_UNIDADE_TRABALHO TO aio;
GRANT SELECT ON tb_usuario TO aio;

------------------------- GRANT RH -----------------
GRANT SELECT ON tb_verba TO aio;
GRANT SELECT ON tb_folha_consisth TO aio;
GRANT SELECT ON tb_vinculo TO aio;
GRANT SELECT ON tb_lotacao_professor TO aio;
GRANT SELECT ON tb_outra_lotacao TO aio;
GRANT SELECT ON tb_atividade TO aio;
GRANT SELECT ON tb_cargo_comissionado TO aio;
GRANT SELECT ON tb_funcionario TO aio;
GRANT SELECT ON tb_periodo_letivo TO aio;
GRANT references ON tb_funcionario TO aio;

GRANT SELECT ON tb_cargo TO aio;
GRANT SELECT ON tb_nivel_classificacao TO aio;
GRANT SELECT ON tb_carreira TO aio;
GRANT SELECT ON tb_provimento TO aio;
GRANT SELECT ON tb_tipo_vinculo TO aio;
GRANT SELECT ON tb_ch_regencia_planejamento TO aio;
GRANT SELECT ON tb_aditivo_ch TO aio;


------------------------- GRANT prefsme_prd_01 -----------------

GRANT SELECT ON rh_frequencia_reposicao_freq TO aio;
GRANT SELECT ON rh_frequencia TO aio;
GRANT SELECT ON rh_frequencia_motivos TO aio;
GRANT SELECT ON serie TO aio;
GRANT SELECT ON rh_frequencia_professores TO aio;
GRANT SELECT ON rh_frequencia_reposicao_prof TO aio;
GRANT SELECT ON rh_frequencia_reposicao TO aio;
GRANT SELECT ON rh_servidor_publico TO aio;
GRANT SELECT ON serie TO aio;
GRANT SELECT ON classe TO aio; 
GRANT SELECT ON disciplina_serie TO aio;
GRANT SELECT ON periodo_do_dia TO aio;
GRANT SELECT ON RH_TIPO_AFASTAMENTO TO aio;
GRANT SELECT ON RH_FALTAS TO aio;

------------------------- GRANT educacao -----------------
GRANT SELECT ON tb_modalidade TO aio;

-------------------------- Mudar o tipo do campo
---------------------------Tabela: prefsme_prd_01.rh_frequencia_professores campo: obs_frequencia para CLOB

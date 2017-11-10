INSERT INTO tb_especialidade(
            ci_especialidade, ds_especialidade, cd_sub_categoria)
    VALUES (nextval('seq_especialidade'), 'Clínico Geral', 1);

INSERT INTO tb_especialidade(
            ci_especialidade, ds_especialidade, cd_sub_categoria)
    VALUES (nextval('seq_especialidade'), 'Ginecologista', 1);

INSERT INTO tb_especialidade(
            ci_especialidade, ds_especialidade, cd_sub_categoria)
    VALUES (nextval('seq_especialidade'), 'Pé', 2);

INSERT INTO tb_especialidade(
            ci_especialidade, ds_especialidade, cd_sub_categoria)
    VALUES (nextval('seq_especialidade'), 'Sushi', 3);

INSERT INTO tb_especialidade(
            ci_especialidade, ds_especialidade, cd_sub_categoria)
    VALUES (nextval('seq_especialidade'), 'Pizza', 3);

INSERT INTO tb_especialidade(
            ci_especialidade, ds_especialidade, cd_sub_categoria)
    VALUES (nextval('seq_especialidade'), 'Carnes', 3);



--select nextval('seq_especialidade')
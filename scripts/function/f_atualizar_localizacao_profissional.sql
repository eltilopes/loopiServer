
  CREATE OR REPLACE FUNCTION f_atualizar_localizacao_profissional()
  RETURNS trigger AS $localizacao_trigger$
  BEGIN
  UPDATE tb_profissional SET cd_localizacao = new.ci_localizacao WHERE ci_profissional = new.cd_profissional;
  RETURN NEW;
  END;
  $localizacao_trigger$ LANGUAGE plpgsql; 

   CREATE TRIGGER localizacao_trigger
  AFTER INSERT ON tb_localizacao
  FOR EACH ROW
  EXECUTE PROCEDURE f_atualizar_localizacao_profissional();

  
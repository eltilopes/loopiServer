package br.com.aio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import br.com.aio.security.entity.Usuario;

@Repository("usuarioDaoTeste")
public class UsuarioDao {
	
	@Inject
	private DataSource dataSource;

	public String getUnusedCpf() throws SQLException{
		Statement stmt = null;
		try {
			stmt = dataSource.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					new StringBuilder().append("select f.nr_cpf from rh.tb_funcionario f ")
							.append("join rh.tb_vinculo v on (v.cd_funcionario = f.ci_funcionario) ")
							.append("where v.cd_tipo_vinculo in (2,4,5,6,7) ")
							.append("and v.dt_aposentadoria is null and v.dt_vacancia is null ")
							.append("and f.nr_cpf not in (select u.nr_cpf from tb_usuario_mobile u)")
							.toString());
			if(rs.next()){
				return rs.getString("nr_cpf");
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally{
			if(Objects.nonNull(stmt)){
				stmt.close();
			}
		}
	}
	
	public String getUnusedLogin() throws SQLException{
		Statement stmt = null;
		try {
			stmt = dataSource.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select SEQ_LOGIN_TESTE.nextval sequence from dual");
			if(rs.next()){
				return "ling"+ rs.getString("sequence") +"@gmail.com";
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally{
			if(Objects.nonNull(stmt)){
				stmt.close();
			}
		}
	}

	public List<Usuario> getUserIdleHours() {
		return null;
	}
}

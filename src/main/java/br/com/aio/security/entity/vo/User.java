package br.com.aio.security.entity.vo;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author Stanley Albuquerque
 * 
 * Classe utilizada para reprensentar um usuário do GLPI.
 * @see <a href="https://github.com/fusioninventory/glpi">GLPI Github</a>
 *
 */

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class User{

	private Integer id;
	
	private String name;
	
	private String password;
	
	private String realname;
	
	private String firstname;
	
	@JsonProperty("use_mode")
	private Integer mode = 0;
	
	public User(){ }
	
	public User(Integer id){ 
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public UserBuilder newUserWith(){
		UserBuilder userBuilder = new UserBuilder();
		return userBuilder;
	}

	public class UserBuilder{
		
		public UserBuilder name(String name){
			User.this.name = name;
			return this;
		}
		
		public UserBuilder realName(String realName){
			User.this.realname = realName;
			return this;
		}
		
		public UserBuilder firstname(String firstname){
			User.this.firstname = firstname;
			return this;
		}
		
		public UserBuilder password(String password){
			User.this.password = password;
			return this;
		}
		
		public User build(){
			return User.this;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		File file = new File("test.json");
		
		ObjectMapper objectMapper = new ObjectMapper();
		JavaType list = objectMapper.getTypeFactory().constructCollectionType(List.class, User.class);
		ObjectReader objectReader = objectMapper.readerFor(list).withRootName("User");
		List<User> result = objectReader.readValue(file);
		
		System.out.println(result.get(0));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

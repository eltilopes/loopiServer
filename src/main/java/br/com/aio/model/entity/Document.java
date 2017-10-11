package br.com.aio.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author Stanley Albuquerque
 * 
 * Esta classe é comunmente utilizada para a representação do objeto Document,
 * similar ao objeto json oferecido pelo GLPI.
 * @see <a href="https://github.com/fusioninventory/glpi">GLPI Github</a>
 *
 */

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Document {
	
	private String id;
	
	private String name;
	
	private String base64;
	
	private String thumb;
	
	private String filepath;
	
	public Document(){}
	
	public Document(String name, String base64){
		this.name = name;
		this.base64 = base64;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

}

package br.com.aio.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Accompaniment implements Comparable<Accompaniment> {

	private String date;
	
	private String content;
	
	private String requesttypes_name;
	
	private String user_name;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRequesttypes_name() {
		return requesttypes_name;
	}

	public void setRequesttypes_name(String requesttypes_name) {
		this.requesttypes_name = requesttypes_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public int compareTo(Accompaniment o) {
		return o.getDate().compareTo(getDate());
	}
	
}

package com.restldap.api.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Employee {

	private String id;
	private String name;
	private String position;
	private String email;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String manager;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String photo;

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}

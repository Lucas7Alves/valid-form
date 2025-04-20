package com.souunit.br.barrier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;
	
	@Size(min = 3, max = 20, message = "The name should be 3 at 20 characters")
	private String name;
	
	@Email
	private String email;
	
	@Size(min = 8, message = "The password should be bigger or equals than 8 characters")
	private String password;
	
	
	public User(){
	}
	
	public User(Long idUser, String name, @Email String email, String password) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setId(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

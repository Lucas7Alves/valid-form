package com.souunit.br.barrier.DTO;

public class TempTokenDTO {
    private String email;
    private String token;
    
    
	public String getEmail() {
		return email != null ? email.toLowerCase() : null;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}

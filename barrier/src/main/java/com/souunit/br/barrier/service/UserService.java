package com.souunit.br.barrier.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souunit.br.barrier.DTO.UserDTO;
import com.souunit.br.barrier.model.User;
import com.souunit.br.barrier.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public UserDTO insert(User u) {
		UserDTO dto = copyUserToDTO(u);
		
		//TODO: enviar email de confirmação para usuário
		if (!isDomainValid(u.getEmail())) {
			throw new RuntimeException("Invalid email!");
		}
		
		repository.save(u);
		return dto;
	}
	
	private UserDTO copyUserToDTO(User u) {
		UserDTO dto = new UserDTO();
		
		dto.setName(u.getName());
		dto.setEmail(u.getEmail());
		return dto;
	}
	
	
	private boolean isDomainValid(String email) {
	    if (email == null || !email.contains("@")) {
	        return false;
	    }
	    String domain = email.split("@")[1];
	    try {
	        InetAddress.getByName(domain);
	        return true;
	    } catch (UnknownHostException e) {
	        return false;
	    }
	}

}

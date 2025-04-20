package com.souunit.br.barrier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souunit.br.barrier.DTO.UserDTO;
import com.souunit.br.barrier.model.User;
import com.souunit.br.barrier.repositories.UserRepository;
import com.souunit.br.barrier.validation.EmailValidator;
import com.souunit.br.barrier.validation.NameValidator;
import com.souunit.br.barrier.validation.PasswordValidator;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public UserDTO insert(User u) {
		
		if (!PasswordValidator.isValid(u.getPassword())) {
			throw new IllegalArgumentException
			("Password required: 8 character or bigger, a upper case and lower case.");
		}
		if (!EmailValidator.isDomainValid(u.getEmail())) {
			throw new IllegalArgumentException("Invalid email!");
		}
		
		if (!NameValidator.isNameValid(u.getName())) {
			throw new IllegalArgumentException("Name required: 3 character or bigger.");
		}
		
		UserDTO dto = copyUserToDTO(repository.save(u));
		return dto;
	}
	
	private UserDTO copyUserToDTO(User u) {
		UserDTO dto = new UserDTO();
		dto.setId(u.getIdUser());
		dto.setName(u.getName());
		dto.setEmail(u.getEmail());
		return dto;
	}

}

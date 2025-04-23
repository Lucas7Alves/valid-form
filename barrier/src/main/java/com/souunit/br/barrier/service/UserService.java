package com.souunit.br.barrier.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.souunit.br.barrier.DTO.UserDTO;
import com.souunit.br.barrier.model.User;
import com.souunit.br.barrier.repositories.UserRepository;
import com.souunit.br.barrier.service.exception.EmailAlreadyExistsException;
import com.souunit.br.barrier.service.exception.InvalidPasswordException;
import com.souunit.br.barrier.service.exception.UserValidationException;
import com.souunit.br.barrier.validation.EmailValidator;
import com.souunit.br.barrier.validation.NameValidator;
import com.souunit.br.barrier.validation.PasswordValidator;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; 
	
	public UserDTO insert(User u) {
		
		if (!PasswordValidator.isValid(u.getPassword())) {
			throw new InvalidPasswordException
			("Password required: 8 character or bigger, a upper case and lower case.");
		}
		if (!EmailValidator.isDomainValid(u.getEmail())) {
			throw new UserValidationException("Invalid email!");
		}
		
		if (!NameValidator.isNameValid(u.getName())) {
			throw new UserValidationException("Name required: 3 character or bigger.");
		}
		
		Optional<User> existingUser = repository.findByEmail(u.getEmail());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("E-mail já está cadastrado.");
        }
		
		u.setPassword(encoder.encode(u.getPassword()));
		u.setSecurityAnswer(encoder.encode(u.getSecurityAnswer()));
		UserDTO dto = copyUserToDTO(repository.save(u));
		return dto;
		
		
	}
	
	public Optional<User> findByEmail(String email) {
		Optional<User> u = repository.findByEmail(email);
		return u;
	}
	private UserDTO copyUserToDTO(User u) {
		UserDTO dto = new UserDTO();
		dto.setId(u.getIdUser());
		dto.setName(u.getName());
		dto.setEmail(u.getEmail());
		return dto;
	}

}

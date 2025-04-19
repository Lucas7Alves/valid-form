package com.souunit.br.barrier.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.souunit.br.barrier.DTO.UserDTO;
import com.souunit.br.barrier.model.User;
import com.souunit.br.barrier.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping(value = "/cadastro")
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody User u) {
		
		UserDTO dto = service.insert(u);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
}

package com.souunit.br.barrier.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.souunit.br.barrier.DTO.LoginDTO;
import com.souunit.br.barrier.DTO.UserAuthDTO;
import com.souunit.br.barrier.DTO.UserDTO;
import com.souunit.br.barrier.model.User;
import com.souunit.br.barrier.security.JwtUtil;
import com.souunit.br.barrier.service.TempTokenService;
import com.souunit.br.barrier.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	//TODO: segundo método de login
	//TODO: enviar email de confirmação para usuário (opcional)

	@Autowired
	private UserService service;
	
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private TempTokenService tempTokenService;
	
	@PostMapping(value = "/cadastro")
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody User u) {
		
		u.setEmail(u.getEmail().toLowerCase());
	    UserDTO dto = service.insert(u);

	    URI uri = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(dto.getId()).toUri();

	    return ResponseEntity.created(uri).body(dto);
	}

	@PostMapping("/auth")
	public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {
	    User user = service.findByEmail(dto.getEmail())
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
	        return ResponseEntity.status(401).body("Invalid password");
	    }

	    // Envia a pergunta de segurança, mas ainda sem gerar o token final
	    Map<String, String> response = new HashMap<>();
	    response.put("securityQuestion", user.getSecurityQuestion());
	    response.put("email", user.getEmail());
	    return ResponseEntity.ok(response);
	}

	
	@PostMapping("/auth/verify")
	public ResponseEntity<?> verifyAnswer(@RequestBody Map<String, String> payload) {
	    String email = payload.get("email");
	    String answer = payload.get("answer");

	    User user = service.findByEmail(email)
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    if (!passwordEncoder.matches(answer, user.getSecurityAnswer())) {
	        return ResponseEntity.status(401).body("Resposta incorreta");
	    }

	    String token = jwtUtil.generateToken(user.getEmail());
	    UserDTO userDto = convertToDto(user);
	    UserAuthDTO response = new UserAuthDTO("Bearer " + token, userDto);
	    
	    return ResponseEntity.ok().body(response);
	}
	
	
	private UserDTO convertToDto(User user) {
	    UserDTO dto = new UserDTO();
	    dto.setId(user.getIdUser());
	    dto.setEmail(user.getEmail());
	    dto.setName(user.getName());
	    return dto;
	}
}

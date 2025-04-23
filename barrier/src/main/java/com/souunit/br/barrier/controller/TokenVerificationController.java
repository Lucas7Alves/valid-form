package com.souunit.br.barrier.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.souunit.br.barrier.DTO.TempTokenDTO;
import com.souunit.br.barrier.service.TempTokenService;

@RestController
@RequestMapping("/verify")
public class TokenVerificationController {

	@Autowired
	private TempTokenService serive;
	
	@PostMapping
	public ResponseEntity<?> verifyToken(@RequestBody TempTokenDTO dto) {
		if (serive.validateToken(dto.getEmail(), dto.getToken())) {
			return ResponseEntity.ok(Map.of("message", "Token válido"));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Token inválido ou expirado"));
		}
	}
}

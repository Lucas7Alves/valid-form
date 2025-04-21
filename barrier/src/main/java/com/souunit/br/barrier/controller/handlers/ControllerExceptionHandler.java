package com.souunit.br.barrier.controller.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.souunit.br.barrier.DTO.CustomError;
import com.souunit.br.barrier.service.exception.EmailAlreadyExistsException;
import com.souunit.br.barrier.service.exception.InvalidPasswordException;
import com.souunit.br.barrier.service.exception.UserNotFoundException;
import com.souunit.br.barrier.service.exception.UserValidationException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<CustomError> emailAlreadyExists(EmailAlreadyExistsException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<CustomError> invalidPassword(InvalidPasswordException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CustomError> userNotFound(UserNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(UserValidationException.class)
	public ResponseEntity<CustomError> userValidation(UserValidationException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
	    HttpStatus status = HttpStatus.BAD_REQUEST;

	    FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
	    String message = fieldError.getDefaultMessage();

	    CustomError err = new CustomError(Instant.now(), status.value(), message, request.getRequestURI());
	    return ResponseEntity.status(status).body(err);
	}

}

package com.souunit.br.barrier.DTO;

import java.time.Instant;

public class CustomError {

	private Instant instant;
	private int status;
	private String error;
	private String path;
	
	public CustomError(Instant instant, int status, String error, String path) {
		this.instant = instant;
		this.status = status;
		this.error = error;
		this.path = path;
	}

	public Instant getInstant() {
		return instant;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getPath() {
		return path;
	}
	
}

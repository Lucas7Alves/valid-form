package com.souunit.br.barrier.validation;

public class NameValidator {
	
	public static boolean isNameValid(String name) {
		if (name.length() < 3) return false;
		return true;
	}

}

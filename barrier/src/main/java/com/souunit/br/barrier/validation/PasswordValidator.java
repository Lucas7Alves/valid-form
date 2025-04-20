package com.souunit.br.barrier.validation;

public class PasswordValidator {

    public static boolean isValid(String password) {
        if (password == null) return false;

        return password.length() >= 8 && 
        		password.matches(".*[A-Z].*") && 
        		password.matches(".*[0-9].*");      
    }
}
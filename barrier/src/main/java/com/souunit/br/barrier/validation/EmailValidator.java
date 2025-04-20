package com.souunit.br.barrier.validation;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EmailValidator {

	public static boolean isDomainValid(String email) {
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

package org.jspider.shopping_kart.exception;

public class InvalidCredentialsException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid Credentials";
	}
}

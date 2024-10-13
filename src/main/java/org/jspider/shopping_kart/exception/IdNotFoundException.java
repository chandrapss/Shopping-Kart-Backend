package org.jspider.shopping_kart.exception;

public class IdNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid Id";
	}

}

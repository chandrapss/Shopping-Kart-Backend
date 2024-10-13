package org.jspider.shopping_kart.dto;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private T data;
	private String message;
	private int statusCode;
}

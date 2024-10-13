package org.jspider.shopping_kart.dto;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmailConfiguration {

	private Map<String, String> user;
	private String subject;
	private String text;
}

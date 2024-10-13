package org.jspider.shopping_kart.controller;

import org.jspider.shopping_kart.dto.Merchant;
import org.jspider.shopping_kart.dto.ResponseStructure;
import org.jspider.shopping_kart.dto.User;
import org.jspider.shopping_kart.service.MerchantService;
import org.jspider.shopping_kart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user, HttpServletRequest request) {
		return service.saveUser(request, user);
	}

	@GetMapping("/user/verify")
	public ResponseEntity<ResponseStructure<String>> verifyUser(@RequestParam String token) {
		return service.verifyUser(token);
	}

	@GetMapping("/user/forgot-password")
	public ResponseEntity<ResponseStructure<String>> forgotPassword(@RequestParam String email,
			HttpServletRequest request) {
		return service.sendResetPasswordLink(email, request);
	}

	@PostMapping("/user/verify-login")
	public ResponseEntity<ResponseStructure<User>> loginVerifyByUser(@RequestParam String email,
			@RequestParam String password) {
		return service.loginVerifyByUser(email, password);
	}
}

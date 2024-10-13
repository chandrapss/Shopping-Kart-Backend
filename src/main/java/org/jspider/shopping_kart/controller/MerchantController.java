package org.jspider.shopping_kart.controller;

import org.jspider.shopping_kart.dto.Merchant;
import org.jspider.shopping_kart.dto.ResponseStructure;
import org.jspider.shopping_kart.dto.User;
import org.jspider.shopping_kart.service.MerchantService;
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

public class MerchantController {
	@Autowired
	private MerchantService service;

	@PostMapping("/merchant")
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant,
			HttpServletRequest request) {
		return service.saveMerchant(merchant, request);
	}

	@GetMapping("/merchant/verify")
	public ResponseEntity<ResponseStructure<String>> verifyMerchant(@RequestParam String token) {
		return service.verifyMerchant(token);
	}

	@GetMapping("/merchant/forgot-password")
	public ResponseEntity<ResponseStructure<String>> forgotPassword(@RequestParam String email,
			HttpServletRequest request) {
		return service.sendResetPasswordLink(email, request);
	}

	@PostMapping("/merchant/verify-login")
	public ResponseEntity<ResponseStructure<Merchant>> loginVerifyByUser(@RequestParam String email,
			@RequestParam String password) {
		return service.loginVerifyByMerchant(email, password);
	}

//	@PostMapping("/merchant")
//	public ResponseEntity<ResponseStructure<Merchant>> updateUser(@RequestBody Merchant merchant, HttpServletRequest request) {
//		return service.saveMerchant(merchant, request);
//	}

//	@PostMapping("/merchant/verifyemailpassword")
//	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam String email, @RequestParam String password) {
//		return service.verifyMerchant(email, password);
//	}

}

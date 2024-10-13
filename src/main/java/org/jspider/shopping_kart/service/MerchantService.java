package org.jspider.shopping_kart.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.jspider.shopping_kart.dao.MerchantDao;
import org.jspider.shopping_kart.dto.EmailConfiguration;
import org.jspider.shopping_kart.dto.Merchant;
import org.jspider.shopping_kart.dto.ResponseStructure;
import org.jspider.shopping_kart.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao dao;
	@Autowired
	private EmailConfiguration configuration;
	@Autowired
	private ShoppingKartMailService mailService;
	@Autowired
	private GenerateLinkService service;

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant, HttpServletRequest request) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setData(dao.saveMerchant(merchant));
		structure.setMessage("Merchant Registration Successful");
		structure.setStatusCode(HttpStatus.CREATED.value());

		HashMap<String, String> map = new LinkedHashMap<>();

		map.put("email", merchant.getEmail());
		map.put("name", merchant.getName());
		configuration
				.setText("Hello Mr." + merchant.getName() + " You have succesfully initiated the Merchant registration"
						+ " Please click on the link " + service.getVerificationLink(request, merchant));
		configuration.setUser(map);
		configuration.setSubject("Registration Successful");
		mailService.sendMail(configuration);
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<String>> verifyMerchant(String token) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Merchant merchant = dao.verifyMerchant(token);
		if (merchant != null) {
			merchant.setToken(null);
			merchant.setStatus("Active");
			dao.updateMerchant(merchant);
			structure.setData("Your Account Is Activated");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Merchant is Verified");

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setData("Your Account Is Not Activated");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Invalid Token");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseStructure<String>> sendResetPasswordLink(String email, HttpServletRequest request) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Merchant merchant = dao.findMerchantByEmail(email);
		if (merchant != null) {
			HashMap<String, String> map = new LinkedHashMap<>();
			map.put("email", merchant.getEmail());
			map.put("name", merchant.getName());

			configuration.setSubject("Reset Password");
			configuration.setUser(map);
			configuration.setText("Hello Mr." + merchant.getName()
					+ " You have requested for password change please click on the following link "
					+ service.getResetPassword(request, merchant));
			mailService.sendMail(configuration);
			structure.setData("Reset password Link send to email");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Main sent to merchant");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		structure.setData("Please Resister");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Merchant Not Found");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<Merchant>> loginVerifyByMerchant(String email, String password) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recallMerchant = dao.loginVerifyByMerchant(email, password);
		if (recallMerchant.isPresent()) {
			structure.setData(recallMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Merchant Login Successful");

			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
//	throw new InvalidCredentialException();
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.NOT_FOUND);

	}
}

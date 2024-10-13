package org.jspider.shopping_kart.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.jspider.shopping_kart.dao.UserDao;
import org.jspider.shopping_kart.dto.EmailConfiguration;
import org.jspider.shopping_kart.dto.ResponseStructure;
import org.jspider.shopping_kart.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {
	@Autowired
	private UserDao dao;

	@Autowired
	private EmailConfiguration configuration;
	@Autowired
	private ShoppingKartMailService mailService;
	@Autowired
	private GenerateLinkService service;

	public ResponseEntity<ResponseStructure<User>> saveUser(HttpServletRequest request, User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(dao.saveUser(user));
		structure.setMessage("User Registration Successful");
		structure.setStatusCode(HttpStatus.CREATED.value());

		HashMap<String, String> map = new LinkedHashMap<>();
		map.put("email", user.getEmail());
		map.put("name", user.getName());
		configuration.setText("Hello Mr." + user.getName() + " You have succesfully initiated the User registration"
				+ " Please click on the link " + service.getVerificationLinkUser(request, user));
		configuration.setUser(map);
		configuration.setSubject("Registration Successful");
		mailService.sendMail(configuration);
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<String>> verifyUser(String token) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		User user = dao.verifyUser(token);
		if (user != null) {
			user.setToken(null);
			user.setStatus("Active");
			dao.updateUser(user);
			structure.setData("Your Account Is Activated");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("User is Verified");

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setData("Your Account Is Not Activated");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Invalid Token");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseStructure<String>> sendResetPasswordLink(String email, HttpServletRequest request) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		User user = dao.findMerchantByEmail(email);
		if (user != null) {
			HashMap<String, String> map = new LinkedHashMap<>();
			map.put("email", user.getEmail());
			map.put("name", user.getName());
			
//			user.setStatus("INActive");
			configuration.setSubject("Reset Password");
			configuration.setUser(map);
			configuration.setText("Hello Mr." + user.getName()
					+ " You have requested for password change please click on the following link "
					+ service.getResetPassword(request, user));
			mailService.sendMail(configuration);
			structure.setData("Reset Password Link send to email");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Mail sent to user");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		structure.setData(" Please Register ");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Invalid Token");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<ResponseStructure<User>> loginVerifyByUser(String email, String password) {
		
		Optional<User> recUser=dao.loginVerifyByUser(email, password);
		ResponseStructure<User> structure=new ResponseStructure<>();
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("User Login Successful");
		
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);
		
//	throw new InvalidCredentialException();
	}	
	
}

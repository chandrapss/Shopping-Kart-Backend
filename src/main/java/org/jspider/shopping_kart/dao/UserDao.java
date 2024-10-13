package org.jspider.shopping_kart.dao;

import java.util.Optional;

import org.jspider.shopping_kart.dto.Merchant;
import org.jspider.shopping_kart.dto.Product;
import org.jspider.shopping_kart.dto.User;
import org.jspider.shopping_kart.repository.MerchantRepository;
import org.jspider.shopping_kart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}

	public User updateUser(User user) {
		return repository.save(user);
	}

	public User verifyUser(String token) {
		return repository.findUserByToken(token);
	}

	public User findMerchantByEmail(String email) {
		return repository.findUserByEmail(email);
	}
	
	public Optional<User> loginVerifyByUser(String email, String password){
		return repository.loginVerifyUser(email, password);
	}
	
	public Optional<User> findById(int id) {
		return repository.findById(id);
	}
}

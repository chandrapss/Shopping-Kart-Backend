package org.jspider.shopping_kart.dao;

import java.util.Optional;

import org.jspider.shopping_kart.dto.Merchant;
import org.jspider.shopping_kart.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository repository;

	public Merchant saveMerchant(Merchant merchant) {
		return repository.save(merchant);
	}

	public Merchant updateMerchant(Merchant merchant) {
		return repository.save(merchant);
	}

	public Merchant verifyMerchant(String token) {
		return repository.findMerchantByToken(token);
	}

	public Merchant findMerchantByEmail(String email) {
		return repository.findMerchantByEmail(email);
	}
	
	public Optional<Merchant> loginVerifyByMerchant(String email, String password){
		return repository.loginVerifyMerchant(email, password);
	}
	
	public Optional<Merchant> findById(int id){
		return repository.findById(id);
	}

	
	
	

}

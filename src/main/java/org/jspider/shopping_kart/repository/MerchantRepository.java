package org.jspider.shopping_kart.repository;

import java.util.Optional;

import org.jspider.shopping_kart.dto.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
	@Query("select m from Merchant m where m.token=?1")
	Merchant findMerchantByToken(String token);
	
	@Query("select m from Merchant m where m.email=?1")
	Merchant findMerchantByEmail(String email);
	
	@Query("select m from Merchant m where m.email=?1 and m.password=?2")
	Optional<Merchant> loginVerifyMerchant(String email, String password);
}

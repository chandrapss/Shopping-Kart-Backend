package org.jspider.shopping_kart.repository;

import java.util.List;
import java.util.Optional;

import org.jspider.shopping_kart.dto.Product;
import org.jspider.shopping_kart.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("select p from Product p where p.merchant.id=?1")
	public List<Product> findProductsByMerchantId(int merchant_id);

	@Query("select m from User m where m.token=?1")
	public User findUserByToken(String token);
	
	@Query("select m from User m where m.email=?1")
	public User findUserByEmail(String email);
	
	@Query("select u from User u where u.email=?1 and u.password=?2")
	public Optional<User> verifyUser(String email, String password);
	
//	@Query("select u.kart from User u where u.id=?2")
//	public List<Product> findProductsInkart(int id);
	
}

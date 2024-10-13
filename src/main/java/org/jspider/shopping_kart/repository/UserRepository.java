package org.jspider.shopping_kart.repository;

import java.util.Optional;

import org.jspider.shopping_kart.dto.Merchant;
import org.jspider.shopping_kart.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.token=?1")
	User findUserByToken(String token);

	@Query("select u from User u where u.email=?1")
	User findUserByEmail(String email);

	@Query("select u from User u where u.email=?1 and u.password=?2")
	Optional<User> loginVerifyUser(String email, String password);

}

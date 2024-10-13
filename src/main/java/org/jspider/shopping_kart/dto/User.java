package org.jspider.shopping_kart.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private long phone;
	private String email;
	private String password;
	private String token;
	private String status;
	@OneToMany
	@JoinTable(name = "user_kart", joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> kart;

	@OneToMany
	@JoinTable(name = "user_wishList", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> wishList;
	
//	@OneToMany(mappedBy = "user")
//	private List<Address> addresses;

}

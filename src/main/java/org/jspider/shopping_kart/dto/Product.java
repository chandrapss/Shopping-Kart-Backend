package org.jspider.shopping_kart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String brand;
	private String category;
	private String description;
	private double cost;
	private String image;
	private double rating;
	private int no_of_user;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Merchant merchant;
}

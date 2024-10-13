package org.jspider.shopping_kart.controller;

import java.util.List;

import org.jspider.shopping_kart.dto.Product;
import org.jspider.shopping_kart.dto.ResponseStructure;
import org.jspider.shopping_kart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin

public class ProductController {
	@Autowired
	private ProductService service;

	@PostMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product, @PathVariable int id) {
		return service.saveProduct(product, id);

	}

	@PutMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product,
			@PathVariable int id) {
		return service.updateProduct(product, id);

	}

	@GetMapping("/products/a/{merchant_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findById(@PathVariable int merchant_id) {
		return service.findProductsByMerchantId(merchant_id);

	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);

	}

	@PostMapping("/product/kart/{pid}/{uid}")
	public ResponseEntity<ResponseStructure<String>> addToKart(@PathVariable int user_id,
			@PathVariable int product_id) {
		return service.addToKart(product_id, user_id);
	}

	@PostMapping("/product/wish/{pid}/{uid}")
	public ResponseEntity<ResponseStructure<String>> addToWishList(@PathVariable int user_id,
			@PathVariable int product_id) {
		return service.addToWishList(product_id, user_id);
	}
	
	@PutMapping("/product/rate/{pid}/{uid}")
	public ResponseEntity<ResponseStructure<Product>> rateProduct(@PathVariable int uid, @PathVariable int pid,
			@RequestParam double rating) {
		return service.rateProduct(uid, pid, rating);
	}
	
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		return service.findAllProducts();
	}
	
//	@GetMapping("/products")
//	public ResponseEntity<ResponseStructure<List<Product>>> findProductsInKart(@PathVariable int id) {
//		return service.findProductsInkart(id);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

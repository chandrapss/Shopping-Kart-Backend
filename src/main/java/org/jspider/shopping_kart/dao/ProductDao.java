package org.jspider.shopping_kart.dao;

import java.util.List;
import java.util.Optional;

import org.jspider.shopping_kart.dto.Product;
import org.jspider.shopping_kart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository repository;

	public Product saveProduct(Product product) {
		return repository.save(product);
	}

	public Product updateProduct(Product product) {
		return repository.save(product);
	}

	public Optional<Product> findById(int id) {
		return repository.findById(id);
	}

	public void deleteProduct(Integer id) {
		 repository.deleteById(id);
	}

	public List<Product> findProductsByMerchantId(int mid) {
		return repository.findProductsByMerchantId(mid);
	}
	
	public List<Product> findAllProducts(){
		return repository.findAll();
	}

//	public List<Product> findAllProductsInkart(int id) {
//		// TODO Auto-generated method stub
//		return repository.findAll();
//	}
	
}

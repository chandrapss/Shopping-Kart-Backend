package org.jspider.shopping_kart.service;

import java.util.List;
import java.util.Optional;

import org.jspider.shopping_kart.dao.MerchantDao;
import org.jspider.shopping_kart.dao.ProductDao;
import org.jspider.shopping_kart.dao.UserDao;
import org.jspider.shopping_kart.dto.Merchant;
import org.jspider.shopping_kart.dto.Product;
import org.jspider.shopping_kart.dto.ResponseStructure;
import org.jspider.shopping_kart.dto.User;
import org.jspider.shopping_kart.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product, int mid) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(mid);
		if (recMerchant.isPresent()) {
			recMerchant.get().getProducts().add(product);
			product.setMerchant(recMerchant.get());
			dao.saveProduct(product);
			merchantDao.updateMerchant(recMerchant.get());
			structure.setData(product);
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Product Added");
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product, int mid) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(mid);
		if (recMerchant.isPresent()) {
			recMerchant.get().getProducts().add(product);
			product.setMerchant(recMerchant.get());
			dao.saveProduct(product);
			merchantDao.updateMerchant(recMerchant.get());
			structure.setData(product);
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Product Added");
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = dao.findById(id);
		if (recProduct.isPresent()) {
			structure.setData(recProduct.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Product Found");
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Product> recProduct = dao.findById(id);
		if (recProduct.isPresent()) {
			dao.deleteProduct(id);
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Product deleted");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByMerchantId(int merchant_id) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(dao.findProductsByMerchantId(merchant_id));
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Product loaded");
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> addToWishList(int product_id, int user_id) {
		Optional<User> recUser = userDao.findById(user_id);
		Optional<Product> recProduct = dao.findById(product_id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recUser.isPresent() && recProduct.isPresent()) {
			recUser.get().getWishList().add(recProduct.get());
			userDao.updateUser(recUser.get());
			structure.setData("Product Added To The WishList");
			structure.setMessage("User And Prodct Found");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> addToKart(int product_id, int user_id) {
		Optional<User> recUser = userDao.findById(user_id);
		Optional<Product> recProduct = dao.findById(product_id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recUser.isPresent() && recProduct.isPresent()) {
			recUser.get().getKart().add(recProduct.get());
			userDao.updateUser(recUser.get());
			structure.setData("Product Added To The Kart");
			structure.setMessage("User And Prodct Found");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> rateProduct(int product_id, int user_id, double rating) {
		Optional<User> recUser = userDao.findById(user_id);
		Optional<Product> recProduct = dao.findById(product_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recUser.isPresent() && recProduct.isPresent()) {
			Product p = recProduct.get();
			int n = p.getNo_of_user();
			double r = p.getRating() * n++;
			rating = (r + rating) / n;
			p.setNo_of_user(n);
			p.setRating(rating);
			dao.updateProduct(p);
			structure.setData(p);
			structure.setMessage("Producted Rated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(dao.findAllProducts());
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("All Product Loaded");
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	
//	public ResponseEntity<ResponseStructure<List<Product>>> findProductsInkart(int id) {
//		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
//		structure.setData(dao.findAllProductsInkart(id));
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("Following are the list of products to kart");
//		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
//	}

}

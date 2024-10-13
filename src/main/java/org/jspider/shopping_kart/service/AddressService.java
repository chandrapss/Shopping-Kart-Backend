//package org.jspider.shopping_kart.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.jspider.shopping_kart.dao.AddressDao;
//import org.jspider.shopping_kart.dao.UserDao;
//import org.jspider.shopping_kart.dto.Address;
//import org.jspider.shopping_kart.dto.ResponseStructure;
//import org.jspider.shopping_kart.dto.User;
//import org.jspider.shopping_kart.exception.IdNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AddressService {
//	@Autowired
//	private AddressDao dao;
//	@Autowired
//	private UserDao userDao;
//
//	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address, int user_id) {
//		Optional<User> recUser = userDao.findById(user_id);
//		if (recUser.isPresent()) {
//			recUser.get().getAddresses().add(address);
//			address.setUser(recUser.get());
//			ResponseStructure<Address> structure = new ResponseStructure<>();
//			structure.setData(dao.saveAddress(address));
//			structure.setMessage("Address Saved Succesfully");
//			structure.setStatusCode(HttpStatus.CREATED.value());
//			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
//		}
//		throw new IdNotFoundException();
//	}
//
//	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address, int user_id) {
//		Optional<User> recUser = userDao.findById(user_id);
//		if (recUser.isPresent()) {
//			recUser.get().getAddresses().add(address);
//			address.setUser(recUser.get());
//			ResponseStructure<Address> structure = new ResponseStructure<>();
//			structure.setData(dao.saveAddress(address));
//			structure.setMessage("Address Updated Succesfully");
//			structure.setStatusCode(HttpStatus.ACCEPTED.value());
//			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);
//		}
//		throw new IdNotFoundException();
//	}
//
//	public ResponseEntity<ResponseStructure<Address>> findAddressById(int id) {
//		ResponseStructure<Address> structure = new ResponseStructure<>();
//		Optional<Address> recAddress = dao.findById(id);
//		if (recAddress.isEmpty()) {
//			structure.setData(null);
//			structure.setMessage("Address Not Found");
//			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
//			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.NOT_FOUND);
//		}
//		throw new IdNotFoundException();
//	}
//
//	public ResponseEntity<ResponseStructure<String>> deleteAddress(int id) {
//		ResponseStructure<String> structure = new ResponseStructure<>();
//		Optional<Address> recAddress = dao.findById(id);
//		if (recAddress.isEmpty()) {
//			structure.setData("Address Not Found");
//			structure.setMessage("Address Not Deleted");
//			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
//			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
//		}
//		throw new IdNotFoundException();
//	}
//
//	public ResponseEntity<ResponseStructure<List<Address>>> findAddressByUserId(int id) {
//		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
//		structure.setData(dao.findAddressByUserId(id));
//		structure.setMessage("The List Of Address For The User");
//		structure.setStatusCode(HttpStatus.OK.value());
//		return new ResponseEntity<ResponseStructure<List<Address>>>(structure, HttpStatus.OK);
//	}
//
//}

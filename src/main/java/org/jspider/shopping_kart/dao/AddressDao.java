//package org.jspider.shopping_kart.dao;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.jspider.shopping_kart.dto.Address;
//import org.jspider.shopping_kart.repository.AddressRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class AddressDao {
//	@Autowired
//	private AddressRepository repository;
//
//	public List<Address> findAddressByUserId(int id) {
//		return repository.findAddressByUserId(id);
//	}
//
//	public Address saveAddress(Address address) {
//		return repository.save(address);
//	}
//
//	public Address updateAddress(Address address) {
//		return repository.save(address);
//	}
//
//	public Optional<Address> findById(int id) {
//		return repository.findById(id);
//	}
//
//	public void deleteAddress(Integer id) {
//		repository.deleteById(id);
//	}
//
//}

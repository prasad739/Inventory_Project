package com.example.admin.service;

import java.util.List;
import java.util.Optional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.admin.Entity.Admin;
import com.example.admin.repo.AdminRepo;

import com.example.admin.exception.*;
import com.example.admin.modal.BookingDetailsDto;
import com.example.admin.modal.CustomerDto;
import com.example.admin.modal.ProductDto;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepo adminRepo;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public Admin createAdmin(Admin admin) {
		Admin ad = adminRepo.findById(admin.getId()).orElse(null);
		if (ad == null) {
			return adminRepo.save(admin);

		} else {
			throw new NoAdminFoundException("Admin Already Exists");
		}
	}

	@Override
	public String deleteAdmin(int id) {
		Optional<Admin> opt = adminRepo.findById(id);
		if (opt.isPresent()) {
			adminRepo.deleteById(id);
			return "Deleted Successfully";
		} else {
			throw new NoAdminFoundException("Admin not found with the given id");
		}

	}

	@Override
	public Admin getAdminbyid(int id) {
		Optional<Admin> opt = adminRepo.findById(id);

		if (opt.isPresent()) {
			Admin a = opt.get();
			adminRepo.findById(id);
			return a;
		} else {
			throw new NoAdminFoundException("Admin not found with the given id");
		}
	}

//
//	public List<ProductResponse> viewAllProducts() {
//		List<ProductResponse> products = webClient.get().uri("/getAllProducts").retrieve()
//				.bodyToMono(new ParameterizedTypeReference<List<ProductResponse>>() {
//				}).block();
//		return products;
//	}
//
	public List<ProductDto> viewAllProducts() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<ProductDto>> response = restTemplate.exchange("http://localhost:8080/getAllProducts/",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDto>>() {
				});
		List<ProductDto> products = response.getBody();
		return products;
	}
//

	public ProductDto getProductById(int productId) {

		try {
			ResponseEntity<ProductDto> responseEntity = restTemplate
					.getForEntity("http://localhost:8080/viewProduct/" + productId, ProductDto.class);
			ProductDto productDto = responseEntity.getBody();
			System.out.println(productDto.getName());
			return productDto;

		} catch (HttpClientErrorException ex) {
			throw new InvalidProductIdException("The given product id does not exist");
		}

	}

	public List<CustomerDto> viewAllCustomers() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<CustomerDto>> response = restTemplate.exchange("http://localhost:8082/getCustomers/",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerDto>>() {
				});
		List<CustomerDto> customers = response.getBody();
		return customers;
	}

	public BookingDetailsDto getBookingById(int bookingId) {

		ResponseEntity<BookingDetailsDto> responseEntity = restTemplate
				.getForEntity("http://localhost:8081/viewBookingDetailsById/" + bookingId, BookingDetailsDto.class);

		BookingDetailsDto responseDto = responseEntity.getBody();
		System.out.println(responseDto.getCustomerName());

		// System.out.println(responseDto.getCustomerDto());
//		newBookingDto.setCustomerId(bookingDto.getCustomerId());
//		newBookingDto.setCharges(bookingDto.getCharges());
//		newBookingDto.setProductId(bookingDto.getProductId());
//		newBookingDto.setDateTime(bookingDto.getDateTime());
//		return newBookingDto;
		return responseDto;

	}

	@Override

	public Admin getAdminbyEmail(String email) throws NoAdminFoundException {

		Optional<Admin> c = adminRepo.findByEmail(email);

		if (c.isPresent()) {

			Admin cus = c.get();

			return cus;

		} else {

			throw new NoAdminFoundException("Admin not found with given Email");

		}

	}

}

package com.prasad.customer.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prasad.customer.Entity.Customer;

@Repository
public interface ICustomerRepostiory extends CrudRepository<Customer, Integer> {
	
	public Optional<Customer> findByEmail(String email);

}

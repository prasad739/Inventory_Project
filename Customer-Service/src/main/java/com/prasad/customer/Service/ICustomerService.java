package com.prasad.customer.Service;

import java.util.List;
import java.util.Optional;

import com.prasad.customer.Entity.Customer;


public interface ICustomerService {
	
	public Customer addCustomer(Customer customer);

	public Customer deleteCustomer(int userid);

	Customer updateCustomer(int userid, Customer customer);

	public List<Customer> getCustomers();

	public Customer getCustomerByid(int customerId);
	
	public String getCustomerEmail(int customerId);
	
	public String getCustomerPassword(int customerId);
	
	public Customer getCustomerByEmail(String email);

}

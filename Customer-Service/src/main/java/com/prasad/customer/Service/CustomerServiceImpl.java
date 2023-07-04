package com.prasad.customer.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasad.customer.Entity.Customer;
import com.prasad.customer.Exception.CustomerNotFoundException;
import com.prasad.customer.Repository.ICustomerRepostiory;
import com.prasad.customer.Exception.CustomerIdNotFoundException;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepostiory customerrepo;

	@Override
	public Customer addCustomer(Customer customer) {
		return customerrepo.save(customer);
	}

	@Override
	public Customer deleteCustomer(int userid) throws CustomerNotFoundException {
		Optional<Customer> custopt = customerrepo.findById(userid);
		if (custopt.isPresent()) {
			Customer c = custopt.get();
			customerrepo.deleteById(userid);
			return c;
		} else {
			throw new CustomerNotFoundException("customer not found");
		}

	}

	@Override
	public Customer updateCustomer(int userid, Customer customer) throws CustomerNotFoundException {
		Optional<Customer> custopt = customerrepo.findById(userid);
		if (custopt.isPresent()) {
			Customer c1 = custopt.get();

			c1.setEmail(customer.getEmail());
			c1.setFirstName(customer.getFirstName());
			c1.setLastName(customer.getLastName());
			c1.setMobileNumber(customer.getMobileNumber());
			c1.setPassword(customer.getPassword());
			c1.setCustomerName(customer.getCustomerName());
			customerrepo.save(c1);
			return c1;
		} else {
			throw new CustomerNotFoundException("customer not found");
		}

	}

	@Override
	public List<Customer> getCustomers() {
		return (List<Customer>) customerrepo.findAll();
	}

	@Override
	public Customer getCustomerByid(int userid) {
		Optional<Customer> opt = customerrepo.findById(userid);

		if (opt.isPresent()) {
			Customer c = opt.get();
			return c;
		} else {
			throw new CustomerNotFoundException("Customer  not found: ");
		}
	}

	@Override
	public String getCustomerEmail(int customerId) {

		Optional<Customer> opt = customerrepo.findById(customerId);
		if (opt.isPresent()) {
			Customer c = opt.get();
			return c.getEmail();
		} else {
			throw new CustomerIdNotFoundException("Customer not found");
		}

	}

	@Override
	public String getCustomerPassword(int customerId) {
		Optional<Customer> opt = customerrepo.findById(customerId);
		if (opt.isPresent()) {
			Customer c = opt.get();
			return c.getPassword();
		} else {
			throw new CustomerIdNotFoundException("Customer not found");
		}

	}

	@Override
	public Customer getCustomerByEmail(String email) {
		Optional<Customer> c = customerrepo.findByEmail(email);

		if (c.isPresent()) {
			Customer cus = c.get();
			return cus;
		} else {
			throw new CustomerNotFoundException("customer not found with email");
		}

	}

}

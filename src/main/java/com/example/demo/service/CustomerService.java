package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	// save single Customer
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return repository.save(customer);
	}

	// save multiple customers
	public List<Customer> saveCustomers(List<Customer> customers) {
		return repository.saveAll(customers);
	}

	// get customer by cid
	public Customer getCustomerById(int cid) {
		return repository.findById(cid).orElse(null);
	}

	// get all customers
	public List<Customer> getCustomers() {
		return repository.findAll();
	}

	public void delete(int cid) {
		repository.deleteById(cid);
	}

	public void updateCustomer(Customer customer) {
		System.out.println(customer.getCid());
		Customer existcustomer = getCustomerById(customer.getCid());
		if (existcustomer != null) {
			existcustomer.setCname(customer.getCname());
			existcustomer.setCid(customer.getCid());
			existcustomer.setMobile(customer.getMobile());
			repository.save(existcustomer);
		}

	}

}

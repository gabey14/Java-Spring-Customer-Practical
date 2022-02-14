package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
@Controller
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}

	@PostMapping("/customers")
	public List<Customer> addCustomers(@RequestBody List<Customer> customers) {
		return service.saveCustomers(customers);
	}

	@GetMapping("/customers/{cid}")
	public Customer getCustomersById(@PathVariable int cid) {
		return service.getCustomerById(cid);
	}

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return service.getCustomers();
	}

	@DeleteMapping("/customer/{cid}")
	public void deleteCustomers(@PathVariable int cid) {
		service.delete(cid);
	}

	// @PutMapping("/customer")
	// public void updateCustomer(@RequestBody Customer customer){
	// service.updateCustomer(customer);
	// }
	//
	@GetMapping("/viewallcustomers")
	public String viewallcustomers(Model model) {
		model.addAttribute("allcustomers", getCustomers());
		return "viewallcustomers";

	}

	@GetMapping("/viewcustomer/{cid}")
	public String viewcustomer(@PathVariable int cid, Model model) {
		model.addAttribute("viewcustomer", getCustomersById(cid));
		return "viewcustomer";
	}

	@GetMapping("/addcustomer")
	public String addcustomer(Model model) {
		model.addAttribute("allcustomers", getCustomers());
		return "addcustomer";
	}

	@GetMapping("/customerform")
	public String customerform(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customerform";
	}

	@PostMapping("/savecustomer")
	public String savecustomer(@ModelAttribute Customer customer) {
		service.saveCustomer(customer);
		return "redirect:/viewallcustomers";
	}

	// Update Code Assignment by Sir

	@GetMapping("/updateform/{cid}")
	public String updateCustomer(@PathVariable Integer cid, Model model) {

		Customer customer = service.getCustomerById(cid);
		model.addAttribute("customer", customer);
		return "updateform";
	}

	@PostMapping("/updatecustomer")
	public String updateCustomer(@ModelAttribute Customer customer) {
		service.updateCustomer(customer);
		return "redirect:/viewallcustomers";
	}

	// Self Implemented
	@GetMapping("/deletecustomer/{cid}")
	public String deleteCustomer(@PathVariable Integer cid) {
		service.delete(cid);
		return "redirect:/viewallcustomers";
	}

}

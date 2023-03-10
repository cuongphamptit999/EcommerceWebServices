package vn.ptit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.customer.Account;
import vn.ptit.entities.customer.Customer;
import vn.ptit.entities.customer.CustomerMember;
import vn.ptit.repositories.customer.CustomerMemberRepository;
import vn.ptit.repositories.customer.CustomerRepository;
import vn.ptit.services.CustomerService;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerController {
	@Autowired
	CustomerMemberRepository customerMemberRepository;
	@Autowired
	CustomerService customerService;
	@Autowired CustomerRepository customerRepository;

	@PostMapping(value = "/register")
	public CustomerMember register(@RequestBody CustomerMember customerMember) {
		customerMember.addAccount(customerMember.getAccount());
		customerMember.addAddress(customerMember.getAddress());
		customerMember.addFullName(customerMember.getFullName());
		return customerMemberRepository.save(customerMember);
	}

	@PostMapping(value = "/login")
	public CustomerMember login(@RequestBody Account account) {
		return customerService.getCustomerMemberByAccount(account);
	}
	
	@GetMapping("/find-all")
	public List<CustomerMember> findAll(){
		return customerMemberRepository.findAll();
	}
	
	@GetMapping("/find-all-customer")
	public List<Customer> findAllCustomer(){
		return customerRepository.findAll();
	}
	
	@PostMapping("/insert-by-social")
	public CustomerMember insertCustomerBySocial(@RequestBody CustomerMember customerMember){
		customerMember.addFullName(customerMember.getFullName());
		if(customerMember.getAddress()!=null) {
			customerMember.addAddress(customerMember.getAddress());
		}
		return customerMemberRepository.save(customerMember);
	}
	
	@GetMapping("/get-by-email/{email}")
	public CustomerMember getByEmail(@PathVariable("email") String email){
		
		return customerService.getCustomerMemberByEmail(email);
	}
	
	@GetMapping("/checkmail-edit/{id}")
	public List<CustomerMember> checkMailEdit(@PathVariable("id") int id){
		return customerService.checkMailEdit(id);
	}
	
	@PostMapping(value = "/edit-profile")
	public CustomerMember editProfile(@RequestBody CustomerMember customerMember) {
		return customerMemberRepository.save(customerMember);
	}
	
	@GetMapping("/get-customer-by-order/{orderId}")
	public Customer customer(@PathVariable("orderId") int orderId) {
		return customerService.getByOrder(orderId);
	}
	
	@GetMapping("/total-customers")
	public Integer totalCustomers(){
		return customerRepository.findAll().size();
	}
	
	@GetMapping("/delete-customer/{id}")
	public Integer deleteCustomer(@PathVariable("id") int id){		
		customerRepository.deleteById(id);
		return 1;
	}
}

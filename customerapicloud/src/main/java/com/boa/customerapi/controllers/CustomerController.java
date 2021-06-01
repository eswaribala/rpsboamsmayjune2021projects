package com.boa.customerapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boa.customerapi.models.Customer;
import com.boa.customerapi.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customers")
@RefreshScope
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Value("${message}")
	private String message;
	@PostMapping({"/v1.0", "/v1.1"})
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
		
		Customer customerObj=this.customerService.addCustomer(customer);
		if(customerObj.getCustomerId()>0) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Data Not correct");
	}
	
	@GetMapping({"/v1.0", "/v1.1"})
	public List<Customer> getAllCustomers(){
		log.info(message);
		return this.customerService.getAllCustomers();
	}
	
	@GetMapping({"/v1.0/{customerId}", "/v1.1/{customerId}"})
	public ResponseEntity<?> getCustomerById(@PathVariable("customerId") long customerId){
		
		Customer customerObj=this.customerService.getCustomerById(customerId);
		if(customerObj!=null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Record Not Found");
	}
	
	@DeleteMapping({"/v1.0/{customerId}", "/v1.1/{customerId}"})
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long customerId){
		
		boolean status=this.customerService.deleteCustomerById(customerId);
		if(status) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Record Deleted");
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Record Not Found");
	}
	
	@PutMapping({"/v1.0/{customerId}", "/v1.1/{customerId}"})
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,@PathVariable("customerId") long customerId){
		
		Customer customerObj=this.customerService.updateCustomer(customer,customerId);
		if(customerObj.getCustomerId()>0) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Data Not correct");
	}
	
	//http://localhost:7070/customers/v1.0/filters/1?fields=customerId,email,contactNo
	@GetMapping({"/v1.0/filters", "/v1.1/filters"})
    public String getFilteredCustomer(@RequestParam(name = "fields", required = false) 
    String fields) 
	{

		List<Customer> customerList = getAllCustomers();
		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);  
		return SquigglyUtils.stringify(mapper, customerList);
		
    }

	
}

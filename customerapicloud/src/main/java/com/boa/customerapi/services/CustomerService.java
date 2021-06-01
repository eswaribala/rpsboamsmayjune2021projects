package com.boa.customerapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boa.customerapi.models.Address;
import com.boa.customerapi.models.Customer;
import com.boa.customerapi.repositories.CustomerRepostiory;

@Service
public class CustomerService {
    @Autowired
	private CustomerRepostiory customerRepository;
	
	//create
    
    public Customer addCustomer(Customer customer) {
    	
    	if(customer.getAddress().size()>0) {
    		customer.getAddress().stream().forEach( address -> {
                address.setCustomer(customer);
            } );
         this.customerRepository.save(customer);
    	}
    	return customer;
    }
    //getall
    
    public List<Customer> getAllCustomers(){
    	return this.customerRepository.findAll();
    }
    
    
    //get
    public Customer getCustomerById(long customerId) {
    	return this.customerRepository.findById(customerId).orElse(null);
    }
    
    //delete
    
    public boolean deleteCustomerById(long customerId) {
    	boolean status=false;
    	this.customerRepository.deleteById(customerId);
    	if(this.getCustomerById(customerId)==null) {
    		status=true;
    	}
    	return status;
    }
    
    //update
    
 public Customer updateCustomer(Customer customer,long customerId) {
    	
	 //Customer customerObj=this.getCustomerById(customerId);
	 customer.setCustomerId(customerId);
    	if(customer.getAddress().size()>0) {
    		customer.getAddress().stream().forEach( address -> {
                address.setCustomer(customer);
            } );
    	
         this.customerRepository.save(customer);
    	}
    	return customer;
    }
	
}

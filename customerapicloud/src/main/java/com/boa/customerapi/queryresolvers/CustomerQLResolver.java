package com.boa.customerapi.queryresolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boa.customerapi.models.Customer;
import com.boa.customerapi.services.CustomerService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class CustomerQLResolver implements GraphQLQueryResolver {
    @Autowired
	private CustomerService customerService;
    
    
    public List<Customer> findAllCustomers(){
    	return this.customerService.getAllCustomers();
    }

    
    public Customer findCustomer(long customerId) {
    	return this.customerService.getCustomerById(customerId);
    }
}

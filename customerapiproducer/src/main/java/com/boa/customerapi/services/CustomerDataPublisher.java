package com.boa.customerapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.boa.customerapi.facades.CustomerStreams;

@Service
public class CustomerDataPublisher {
	private final CustomerStreams customerStreams;
	   @Autowired
	private CustomerService customerService;
	public CustomerDataPublisher(CustomerStreams customerStreams)
	{
		this.customerStreams=customerStreams;
	}
	public boolean sendCustomerDetails(long customerId)
	{
		//JPA code
		//skeleton
		//Notification
		MessageChannel messageChannel = customerStreams.outboundInventory();
	       return  messageChannel.send(MessageBuilder
	                .withPayload(customerService.getCustomerById(customerId))
	                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
	                .build());

		
	}

}

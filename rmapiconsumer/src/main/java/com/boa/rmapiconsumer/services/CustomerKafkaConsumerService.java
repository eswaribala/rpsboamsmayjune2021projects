package com.boa.rmapiconsumer.services;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.boa.rmapiconsumer.models.CustomerHistory;
import com.boa.rmapiconsumer.repositories.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class CustomerKafkaConsumerService {
	@Autowired
	private CustomerRepository customerRespository;
	private final Logger logger 
		= LoggerFactory.getLogger(CustomerKafkaConsumerService.class);
	
	@KafkaListener(topics = "${customer.topic.name}", 
			groupId = "${customer.topic.group.id}")
	public void consume(String message) {
		logger.info(String.format("Message recieved -> %s", message));
		CustomerHistory customerHistory=new CustomerHistory();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String,Object> map = mapper.readValue(message, Map.class);
			
			
			Iterator itr=map.entrySet().iterator();
			Map.Entry<String, Object> entry=null;
			while(itr.hasNext()) {
				entry=(Entry<String, Object>) itr.next();
				System.out.println(entry.getKey()+","+entry.getValue());
			}
			customerHistory.setHistoryId(Integer.parseInt(map.get("customerId").toString())+new Random().nextInt(10000));
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		
		Gson gson=new Gson();
		String[] data=message.split(",");
		CustomerHistory customerHistory=new CustomerHistory();
		customerHistory= gson.fromJson(message, CustomerHistory.class);
		if(Integer.parseInt(data[0])>0)
		{
		customerHistory.setHistoryId(Integer.parseInt(data[0])+new Random().nextInt(100000));
		customerHistory.setCustomerId(Integer.parseInt(data[0]));
		customerHistory.setName(data[1]);
		customerHistory.setEmail(data[2]);
		customerHistory.setDob(data[4]);
		customerRespository.save( customerHistory);
		logger.info(String.format("Customer Status Stored -> %s,%s", 
	
				customerHistory.getHistoryId(),customerHistory.getCustomerId()));
		
		}
		else
		{
			logger.info(String.format("Customer Status  -> %s", 
					data[1]));
		}
		*/
	}

	

}

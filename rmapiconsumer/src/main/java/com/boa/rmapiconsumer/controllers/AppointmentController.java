package com.boa.rmapiconsumer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boa.rmapiconsumer.services.KafKaProducerService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	private KafKaProducerService kafkaProducerService;
	
	@GetMapping("/v1.0")
	public void publishPlannedDeliveryDate()
	{
		
		kafkaProducerService.sendMessage();
		
	}


}

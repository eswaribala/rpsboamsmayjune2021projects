package com.boa.rmapiconsumer.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.boa.rmapiconsumer.models.AppointmentSchedule;
import com.boa.rmapiconsumer.repositories.AppointmentRepository;



@Service
public class KafKaProducerService 
{
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	private static final Logger logger = 
			LoggerFactory.getLogger(KafKaProducerService.class);
	
	//1. General topic with string payload
	
	@Value(value = "${appointment.topic.name}")
    private String appointmentTopicName;
	
	@Autowired
    private KafkaTemplate<String, AppointmentSchedule> kafkaTemplate;
	
	public void sendMessage(long productId) 
	{
		AppointmentSchedule appointmentSchedule=new AppointmentSchedule();
		appointmentSchedule.setCustomerId(new Random().nextInt(10000));
		appointmentSchedule.setRequestedDate(LocalDate.now());
		/*
		List<StockStatusHistory> stockStatusHistoryList=shippingRepository.findAll();
		StockStatusHistory stockStatusHistory=stockStatusHistoryList.stream()
		.sorted((s1,s2)->s1.getTimeStamp().compareTo(s2.getTimeStamp()))
		.filter(stockStatus->stockStatus.getProductId()==productId)
		.findFirst().orElse(null);
		
		if(stockStatusHistory!=null)
		{
		*/
			//delSchedule.setAvailableQty(stockStatusHistory.getQty());
			appointmentSchedule.setPlannedAppointmentDate(LocalDate.now().plusWeeks(2));
		//}		
		
		ListenableFuture<SendResult<String, AppointmentSchedule>> future 
			= this.kafkaTemplate.send(appointmentTopicName, appointmentSchedule);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, AppointmentSchedule>>() {
            @Override
            public void onSuccess(SendResult<String, AppointmentSchedule> result) {
            	logger.info("Sent message: " + appointmentSchedule.getPlannedAppointmentDate().toString() 
            			+ " with offset: " + result.getRecordMetadata().offset());
            	System.out.println("Sent message: " + appointmentSchedule.getPlannedAppointmentDate().toString() 
            			+ " with offset: " + result.getRecordMetadata().offset());
            }
            

            @Override
            public void onFailure(Throwable ex) {
            	logger.error("Unable to send appointment Date for Customer : " + appointmentSchedule.getCustomerId(), ex);
            }
       });
	}
	
	
}

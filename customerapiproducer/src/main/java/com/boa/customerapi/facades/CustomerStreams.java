package com.boa.customerapi.facades;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomerStreams {
	
    String OUTPUT = "customer-out";
   
    @Output(OUTPUT)
    MessageChannel outboundInventory();

}

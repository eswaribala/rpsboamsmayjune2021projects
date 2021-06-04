package com.boa.rmapiconsumer.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="customers")
@Data
public class CustomerHistory {

	@Id
	private long historyId;
	private long customerId;
	private String name;
	private String email;
	private String dob;
	private String address;
}

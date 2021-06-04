package com.boa.rmapiconsumer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boa.rmapiconsumer.models.CustomerHistory;

public interface CustomerRepository extends MongoRepository<CustomerHistory,Long>{

}

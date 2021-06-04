package com.boa.rmapiconsumer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boa.rmapiconsumer.models.AppointmentSchedule;


public interface AppointmentRepository extends MongoRepository<AppointmentSchedule,Long>{

}

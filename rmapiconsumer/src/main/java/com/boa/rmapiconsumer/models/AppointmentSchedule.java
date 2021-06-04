package com.boa.rmapiconsumer.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AppointmentSchedule {
	private long customerId;
	private LocalDate requestedDate;
	private LocalDate plannedAppointmentDate;
	
	

}

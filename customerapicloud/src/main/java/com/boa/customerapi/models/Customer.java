package com.boa.customerapi.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Customer_Id")
	@ApiModelProperty(position = 1, required = true, hidden=true, notes = "Auto generated column")
	private long customerId;
	@Embedded
	private FullName name;
	@Column(name="Email")
	private String email;
	@Column(name="Contact_No")
	private long contactNo;
	@DateTimeFormat(iso = ISO.DATE)
	  @JsonDeserialize(using = LocalDateDeserializer.class)
	  @JsonSerialize(using = LocalDateSerializer.class)
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name="DOB")
	private LocalDate dob;
	/**
	 * addresses:[{
	 * },{}]
	 */
	@OneToMany(mappedBy ="customer", cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
	@JsonProperty("addresses")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)	
	private List<Address> address;
}

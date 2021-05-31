package com.boa.customerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boa.customerapi.models.Customer;

public interface CustomerRepostiory extends JpaRepository<Customer,Long>{

}

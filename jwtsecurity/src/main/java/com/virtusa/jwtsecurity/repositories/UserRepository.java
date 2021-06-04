package com.virtusa.jwtsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.jwtsecurity.models.User;

public interface UserRepository extends JpaRepository<User,String>{

}

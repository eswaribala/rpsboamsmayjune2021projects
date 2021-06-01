package com.boa.customerapi.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.vault.config.SecretBackendConfigurer;
import org.springframework.cloud.vault.config.VaultConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;


@ConfigurationProperties
@Data
public class VaultConfiguration {

	
  private String username;

  private String password;

  
 
  
}


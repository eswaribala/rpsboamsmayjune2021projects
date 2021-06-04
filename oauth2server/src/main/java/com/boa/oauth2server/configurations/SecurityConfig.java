package com.boa.oauth2server.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/login**", "/error**").permitAll()
		        .anyRequest().authenticated()
		        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
		        .and().oauth2Login();
	}

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		List<ClientRegistration> registrations = new ArrayList<>();
		registrations.add(githubClientRegistration());
		//registrations.add(googleClientRegistration());
		return new InMemoryClientRegistrationRepository(registrations);
	}

	private ClientRegistration githubClientRegistration() {
		return ClientRegistration.withRegistrationId("github")
                                .clientId("d69c8420a1673bcec9d9")
				.clientSecret("115fbecbf7c3230ac250238032a49705012e86f1")
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUriTemplate("http://localhost:8081/login/oauth2/code/{registrationId}")
				.authorizationUri("https://github.com/login/oauth/authorize")
				.tokenUri("https://github.com/login/oauth/access_token")
                                .userInfoUri("https://api.github.com/parameswaribala@gmail.com")
				.clientName("GitHub").build();
	}


} 

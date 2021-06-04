package com.boa.oauth2server.controllers;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@GetMapping("/welcome")
	public ModelAndView welcome(Principal principal) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("welcome");
	    mav.addObject("name", principal.getName());
		return mav;
	}
} 
package com.esra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/loginForm")
	public String showLoginForm() {
		return "custom-login";
	}
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}

}

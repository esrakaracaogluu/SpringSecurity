package com.esra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Welcome {
	@RequestMapping("/test")
	public String test() {
		return "welcome";
	}
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
}

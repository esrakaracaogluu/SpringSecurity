package com.esra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personel")
public class PersonelController {
	
	@RequestMapping("/home")
	public String personelPage() {
		return "personel-home";
	}

	@RequestMapping("/task")
	public String personelTasks() {
		return "personel-tasks";
	}
}

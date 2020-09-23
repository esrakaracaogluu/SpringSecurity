package com.esra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/home")
	public String adminPage() {
		return "admin-home";
	}

	@RequestMapping("/task")
	public String adminTasks() {
		return "admin-tasks";
	}
}

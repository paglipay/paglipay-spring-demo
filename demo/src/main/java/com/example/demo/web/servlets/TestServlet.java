package com.example.demo.web.servlets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestServlet {

	@GetMapping("/test")
	public String test() {
		return "test works";
	}
	
}

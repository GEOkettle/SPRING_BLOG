package com.geo.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JSPControllerTest {
	
	
	//http://localhost:8000/blog/jsp
	@GetMapping("/jsp")
	
	public String tempHome() {
		
		System.out.println("임시거처");
		
		return "app2";
	}

}


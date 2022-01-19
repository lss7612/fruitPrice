package com.pt.fruit.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TaskController {

	@GetMapping("/main")
	public String main() {
		System.out.println("main 접속");
		return "main/main";
	}
}

package com.project.book.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@Tag(name = "Test")
public class TestController {
	@GetMapping("/test")
	public String loginPage() {
		return "index";
	}

	@GetMapping("/success")
	public String successPage() {
		return "success";
	}
}

package org.birum.home.services.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/")
	public String testMethod() {
		return "Working in services namespace";
	}
}

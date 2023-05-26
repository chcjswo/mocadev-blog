package me.mocadev.mocadevblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-05-26
 **/
@RestController
public class MainController {

	@GetMapping("/")
	public String main() {
		return "main page";
	}

	@GetMapping("/user")
	public String user() {
		return "user page";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin page";
	}
}

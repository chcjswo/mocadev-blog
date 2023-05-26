package me.mocadev.mocadevblog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.config.AppConfig;
import me.mocadev.mocadevblog.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-07
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final AppConfig appConfig;

	@GetMapping("/auth/login")
	public String login() {
		return "login page";
	}
}

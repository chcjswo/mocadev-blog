package me.mocadev.mocadevblog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.request.LoginDto;
import me.mocadev.mocadevblog.response.SessionResponseDto;
import me.mocadev.mocadevblog.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/auth/login")
	public SessionResponseDto login(@RequestBody LoginDto loginDto) {
		log.info("login >>> {}", loginDto);
		final String accessToken = authService.sign(loginDto);
		return new SessionResponseDto(accessToken);
	}
}

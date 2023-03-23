package me.mocadev.mocadevblog.controller;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.request.LoginDto;
import me.mocadev.mocadevblog.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
		final String accessToken = authService.sign(loginDto);
		ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
			.domain("localhost") // TODO: 2023-03-23 서버 환경에 따른 분리 필요
			.path("/")
			.httpOnly(true)
			.secure(false)
			.maxAge(Duration.ofDays(30))
			.sameSite("Strict")
			.build();
		return ResponseEntity.ok()
			.header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
	}
}

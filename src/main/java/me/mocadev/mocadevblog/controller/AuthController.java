package me.mocadev.mocadevblog.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;
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
	private static final String KEY = "s27bPAGI6zwnDIpvxE8cqcJLKCSpZel7cVg+JAVM43A=";

	@PostMapping("/auth/login")
	public SessionResponseDto login(@RequestBody LoginDto loginDto) {
		final Long userId = authService.sign(loginDto);

		SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));
		String jws = Jwts.builder()
			.setSubject(String.valueOf(userId))
			.signWith(secretKey)
			.compact();

		return new SessionResponseDto(jws);
	}
}

package me.mocadev.mocadevblog.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.config.AppConfig;
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
	private final AppConfig appConfig;

	@PostMapping("/auth/login")
	public SessionResponseDto login(@RequestBody LoginDto loginDto) {
		final Long userId = authService.sign(loginDto);

//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		byte[] encode = Base64.getEncoder().encode(key.getEncoded());

		SecretKey secretKey = Keys.hmacShaKeyFor(appConfig.getSecretKey());
		String jws = Jwts.builder()
			.setSubject(String.valueOf(userId))
			.signWith(secretKey)
			.setExpiration(new Date())
			.compact();

		return new SessionResponseDto(jws);
	}
}

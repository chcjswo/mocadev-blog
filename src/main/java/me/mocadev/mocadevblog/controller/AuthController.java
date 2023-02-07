package me.mocadev.mocadevblog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.domain.User;
import me.mocadev.mocadevblog.exception.InvalidLogin;
import me.mocadev.mocadevblog.repository.UserRepository;
import me.mocadev.mocadevblog.request.LoginDto;
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

	private final UserRepository userRepository;

	@PostMapping("/auth/login")
	public User login(@RequestBody LoginDto loginDto) {
		log.info("login >>> {}", loginDto);

		final User user = userRepository.findByEmailAndPassword(
			loginDto.getEmail(), loginDto.getPassword())
			.orElseThrow(InvalidLogin::new);

		return user;
	}
}

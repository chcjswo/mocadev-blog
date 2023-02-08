package me.mocadev.mocadevblog.service;

import lombok.RequiredArgsConstructor;
import me.mocadev.mocadevblog.domain.Session;
import me.mocadev.mocadevblog.domain.User;
import me.mocadev.mocadevblog.exception.InvalidLogin;
import me.mocadev.mocadevblog.repository.UserRepository;
import me.mocadev.mocadevblog.request.LoginDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-09
 **/
@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;

	@Transactional
	public String sign(LoginDto loginDto) {
		final User user = userRepository.findByEmailAndPassword(
				loginDto.getEmail(), loginDto.getPassword())
			.orElseThrow(InvalidLogin::new);

		final Session session = user.addSession();
		return session.getAccessToken();
	}
}

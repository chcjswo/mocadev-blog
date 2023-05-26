package me.mocadev.mocadevblog.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-05-27
 **/
public class UserPrincipal extends User {

	private final Long userId;

	public UserPrincipal(me.mocadev.mocadevblog.domain.User user) {
		super(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority("ADMIN")));
		this.userId = user.getId();
	}

	public Long getUserId() {
		return userId;
	}
}

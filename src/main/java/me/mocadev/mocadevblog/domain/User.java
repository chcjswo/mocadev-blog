package me.mocadev.mocadevblog.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-07
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;
	private String password;
	private LocalDateTime createdAt;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Session> sessions = new ArrayList<>();

	@Builder
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.createdAt = LocalDateTime.now();
	}

	public Session addSession() {
		final Session session = Session.builder()
			.user(this)
			.build();
		sessions.add(session);
		return session;
	}
}
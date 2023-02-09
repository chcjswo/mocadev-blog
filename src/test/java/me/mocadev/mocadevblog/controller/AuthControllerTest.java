package me.mocadev.mocadevblog.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.mocadev.mocadevblog.domain.Session;
import me.mocadev.mocadevblog.domain.User;
import me.mocadev.mocadevblog.repository.SessionRepository;
import me.mocadev.mocadevblog.repository.UserRepository;
import me.mocadev.mocadevblog.request.LoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-09
 **/
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@BeforeEach
	void after() {
		userRepository.deleteAll();
	}

	@Test
	@DisplayName("로그인 성공 후 세션 1개 생성")
	void test() throws Exception {
		// given
		userRepository.save(User.builder()
			.email("chcjswo@gmail.com")
			.name("chcjswo")
			.password("1234")
			.build());

		LoginDto dto = LoginDto.builder()
			.email("chcjswo@gmail.com")
			.password("1234")
			.build();

		mockMvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(""));
	}

	@Test
	@Transactional
	@DisplayName("로그인 성공 후 세션 1개 생성")
	void test2() throws Exception {
		// given
		User savedUser = userRepository.save(User.builder()
			.email("chcjswo@gmail.com")
			.name("chcjswo")
			.password("1234")
			.build());

		LoginDto dto = LoginDto.builder()
			.email("chcjswo@gmail.com")
			.password("1234")
			.build();

		mockMvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isOk());

		final User user = userRepository.findById(savedUser.getId()).get();

		assertThat(sessionRepository.count()).isEqualTo(user.getSessions().size());
	}

	@Test
	@DisplayName("로그인 후 권한이 필요한 페이지 접속")
	void test3() throws Exception {
		// given
		final User user = User.builder()
			.email("chcjswo@gmail.com")
			.name("chcjswo")
			.password("1234")
			.build();
		final Session session = user.addSession();
		userRepository.save(user);

		mockMvc.perform(get("/foo")
				.header("Authorization", session.getAccessToken())
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("로그인 후 검증되지 않은 세션값으로 권한이 필요한 페이지 접속 시 오류")
	void test4() throws Exception {
		// given
		final User user = User.builder()
			.email("chcjswo@gmail.com")
			.name("chcjswo")
			.password("1234")
			.build();
		final Session session = user.addSession();
		userRepository.save(user);

		mockMvc.perform(get("/foo")
				.header("Authorization", session.getAccessToken() + "_test")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isUnauthorized());
	}
}

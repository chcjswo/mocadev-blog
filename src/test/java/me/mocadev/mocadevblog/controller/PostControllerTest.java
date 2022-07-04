package me.mocadev.mocadevblog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.mocadev.mocadevblog.request.PostSaveDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-03
 **/
@WebMvcTest
class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("/posts 요청 출력")
	void getPostsTest() throws Exception {
		mockMvc.perform(get("/posts"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("Hello Mocadev Blog"));
	}

	@Test
	@DisplayName("/post 등록")
	void savePostTest() throws Exception {
		PostSaveDto dto = PostSaveDto.builder()
			.title("제목")
			.content("글 내용")
			.build();

		mockMvc.perform(post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("{}"));
	}

	@Test
	@DisplayName("/post 등록 - title이 없는 경우")
	void savePostFail1Test() throws Exception {
		PostSaveDto dto = PostSaveDto.builder()
			.title(null)
			.content("글 내용")
			.build();

		mockMvc.perform(post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.code").value("400"))
			.andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
			.andExpect(jsonPath("$.validation.title").value("제목은 필수입니다."));
	}
}

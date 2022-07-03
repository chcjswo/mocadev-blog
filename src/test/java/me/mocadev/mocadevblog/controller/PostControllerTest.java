package me.mocadev.mocadevblog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
		mockMvc.perform(post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\": \"글 제목\", \"content\": \"글 내용\"}"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("Hello Mocadev Blog"));
	}
}

package me.mocadev.mocadevblog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.mocadev.mocadevblog.domain.Post;
import me.mocadev.mocadevblog.repository.PostRepository;
import me.mocadev.mocadevblog.request.PostSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-03
 **/
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PostRepository postRepository;

	@BeforeEach
	void after() {
		postRepository.deleteAll();
	}

	@Test
	@DisplayName("/posts 요청 출력")
	void getPostsTest() throws Exception {
		mockMvc.perform(get("/posts"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("Hello Mocadev Blog"));
	}

	@Test
	@DisplayName("/post 등록 내용 출력")
	void savePostStringTest() throws Exception {
		PostSaveDto dto = PostSaveDto.builder()
			.title("제목")
			.content("글 내용")
			.build();

		mockMvc.perform(post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(""));
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

	@Test
	@DisplayName("/post 등록")
	void savePostTest() throws Exception {
		PostSaveDto dto = PostSaveDto.builder()
			.title("제목")
			.content("내용")
			.build();

		mockMvc.perform(post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isOk());

		assertEquals(1L, postRepository.count());

		final Post post = postRepository.findAll().get(0);
		assertEquals("제목", post.getTitle());
		assertEquals("내용", post.getContent());
	}

	@Test
	@DisplayName("글 1개 조회")
	void test() throws Exception {
		// given
		final Post requestPost = Post.builder()
			.title("제목")
			.content("내용")
			.build();
		postRepository.save(requestPost);

		// when & then
		mockMvc.perform(get("/posts/{postId}", requestPost.getId())
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(requestPost.getId()))
			.andExpect(jsonPath("$.title").value(requestPost.getTitle()))
			.andExpect(jsonPath("$.content").value(requestPost.getContent()));
	}
}

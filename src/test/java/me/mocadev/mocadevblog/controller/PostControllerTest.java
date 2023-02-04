package me.mocadev.mocadevblog.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.mocadev.mocadevblog.domain.Post;
import me.mocadev.mocadevblog.repository.PostRepository;
import me.mocadev.mocadevblog.request.PostEditDto;
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
				.header("authorization", "chcjswo")
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

	@Test
	@DisplayName("글 전체 조회")
	void findPostsTest() throws Exception {
		// given
		final List<Post> requestPosts = IntStream.range(1, 31)
			.mapToObj(i ->
				Post.builder()
					.title("제목 " + i)
					.content("내용 " + i)
					.build())
			.collect(Collectors.toList());
		postRepository.saveAll(requestPosts);

		// when & then
		mockMvc.perform(get("/posts?page=1&size=5")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()", is(5)));
	}

	@Test
	@DisplayName("글 수정")
	void updatePostTest() throws Exception {
		// given
		final Post post = Post.builder()
			.title("제목")
			.content("내용")
			.build();
		postRepository.save(post);

		final PostEditDto postEditDto = PostEditDto.builder()
			.title("제목 수정")
			.content("내용 수정")
			.build();

		// when & then
		mockMvc.perform(patch("/posts/{postId}", post.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(postEditDto)))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("글 삭제")
	void deletePostTest() throws Exception {
		// given
		final Post post = Post.builder()
			.title("제목")
			.content("내용")
			.build();
		postRepository.save(post);

		// when & then
		mockMvc.perform(delete("/posts/{postId}", post.getId())
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("존재하지 않는 글 조회")
	void test2() throws Exception {
		mockMvc.perform(get("/posts/{postId}", 1L)
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("게시글 작성 시 잘못된 단어 체크")
	void savePostInvalidRequestTest() throws Exception {
		PostSaveDto dto = PostSaveDto.builder()
			.title("제목 - 바보")
			.content("내용")
			.build();

		mockMvc.perform(post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
}

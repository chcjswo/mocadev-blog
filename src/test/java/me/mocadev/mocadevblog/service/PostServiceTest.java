package me.mocadev.mocadevblog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import me.mocadev.mocadevblog.domain.Post;
import me.mocadev.mocadevblog.repository.PostRepository;
import me.mocadev.mocadevblog.request.PostSaveDto;
import me.mocadev.mocadevblog.response.PostResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-09
 **/
@SpringBootTest
class PostServiceTest {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostService postService;

	@BeforeEach
	void after() {
		postRepository.deleteAll();
	}

	@Test
	@DisplayName("글 작성")
	void writeTest() {
		final PostSaveDto dto = PostSaveDto.builder()
			.title("제목")
			.content("내용")
			.build();

		postService.write(dto);

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

		// when
		final PostResponseDto response = postService.findPostById(requestPost.getId());

		// then
		assertNotNull(response);
		assertEquals("제목", response.getTitle());
		assertEquals("내용", response.getContent());
	}

	@Test
	@DisplayName("글 전체 조회")
	void findPostsTest() throws Exception {
		// given
		Post requestPost = Post.builder()
			.title("제목")
			.content("내용")
			.build();
		postRepository.save(requestPost);
		requestPost = Post.builder()
			.title("제목2")
			.content("내용2")
			.build();
		postRepository.save(requestPost);

		// when
		final List<PostResponseDto> posts = postService.findPosts();

		// then
		assertNotNull(posts);
		assertEquals(2L, postRepository.count());
	}
}

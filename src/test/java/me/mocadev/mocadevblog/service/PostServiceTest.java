package me.mocadev.mocadevblog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.mocadev.mocadevblog.domain.Post;
import me.mocadev.mocadevblog.repository.PostRepository;
import me.mocadev.mocadevblog.request.PostEditDto;
import me.mocadev.mocadevblog.request.PostSaveDto;
import me.mocadev.mocadevblog.request.PostSearch;
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
@DisplayName("포스트 서비스 테스트")
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
	void test() {
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
	@DisplayName("글 1페이지 조회")
	void findPostsTest() {
		// given
		final List<Post> requestPosts = IntStream.range(1, 31)
			.mapToObj(i ->
				Post.builder()
					.title("제목 " + i)
					.content("내용 " + i)
					.build())
			.collect(Collectors.toList());
		postRepository.saveAll(requestPosts);

		PostSearch postSearch = PostSearch.builder()
			.page(1)
			.size(10)
			.build();

		// when
		final List<PostResponseDto> posts = postService.findPosts(postSearch);

		// then
		assertNotNull(posts);
		assertEquals(10L, posts.size());
	}

	@Test
	@DisplayName("글 제목 수정")
	void updatePostTest() {
		// given
		final Post post = Post.builder()
			.title("제목")
			.content("내용")
			.build();
		postRepository.save(post);

		final PostEditDto postEditDto = PostEditDto.builder()
			.title("제목 수정")
			.build();

		// when
		postService.edit(post.getId(), postEditDto);

		// then
		final Post changePost = postRepository.findById(post.getId())
			.orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id = " + post.getId()));

		assertEquals("제목 수정", changePost.getTitle());
	}
}

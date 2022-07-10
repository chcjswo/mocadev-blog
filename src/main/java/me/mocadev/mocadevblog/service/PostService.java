package me.mocadev.mocadevblog.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.domain.Post;
import me.mocadev.mocadevblog.repository.PostRepository;
import me.mocadev.mocadevblog.request.PostSaveDto;
import me.mocadev.mocadevblog.response.PostResponseDto;
import org.springframework.stereotype.Service;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-08
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public void write(PostSaveDto postSaveDto) {
		final Post post = Post.builder()
			.title(postSaveDto.getTitle())
			.content(postSaveDto.getContent())
			.build();
		postRepository.save(post);
	}

	public PostResponseDto findPostById(Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

		return PostResponseDto.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.build();
	}

	public List<PostResponseDto> findPosts() {
		 return postRepository.findAll().stream()
			 .map(PostResponseDto::new)
			 .collect(Collectors.toList());
	}
}

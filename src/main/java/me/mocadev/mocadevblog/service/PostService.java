package me.mocadev.mocadevblog.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.domain.Post;
import me.mocadev.mocadevblog.domain.PostEditor;
import me.mocadev.mocadevblog.domain.PostEditor.PostEditorBuilder;
import me.mocadev.mocadevblog.exception.PostNotFoundException;
import me.mocadev.mocadevblog.repository.PostRepository;
import me.mocadev.mocadevblog.request.PostEditDto;
import me.mocadev.mocadevblog.request.PostSaveDto;
import me.mocadev.mocadevblog.request.PostSearch;
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

	@Transactional
	public void write(PostSaveDto postSaveDto) {
		final Post post = Post.builder()
			.title(postSaveDto.getTitle())
			.content(postSaveDto.getContent())
			.build();
		postRepository.save(post);
	}

	public PostResponseDto findPostById(Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(PostNotFoundException::new);

		return PostResponseDto.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.build();
	}

	public List<PostResponseDto> findPosts(PostSearch postSearch) {
		return postRepository.getList(postSearch).stream()
			.map(PostResponseDto::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void edit(Long id, PostEditDto postEditDto) {
		final Post post = postRepository.findById(id)
			.orElseThrow(PostNotFoundException::new);

		final PostEditorBuilder postEditorBuilder = post.toEditor();
		final PostEditor postEditor = postEditorBuilder.title(postEditDto.getTitle())
			.content(postEditDto.getContent())
			.build();

		post.edit(postEditor);
	}

	public void deletePost(Long id) {
		final Post post = postRepository.findById(id)
			.orElseThrow(PostNotFoundException::new);

		postRepository.delete(post);
	}
}

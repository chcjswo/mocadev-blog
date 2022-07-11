package me.mocadev.mocadevblog.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.request.PostSaveDto;
import me.mocadev.mocadevblog.request.PostSearch;
import me.mocadev.mocadevblog.response.PostResponseDto;
import me.mocadev.mocadevblog.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-03
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/posts")
	public void savePost(@Valid @RequestBody PostSaveDto params) {
		postService.write(params);
	}

	@GetMapping("/posts/{postId}")
	public PostResponseDto findPostById(@PathVariable Long postId) {
		return postService.findPostById(postId);
	}

	@GetMapping("/posts")
	public List<PostResponseDto> findPosts(@ModelAttribute PostSearch postSearch) {
		return postService.findPosts(postSearch);
	}

}

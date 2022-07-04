package me.mocadev.mocadevblog.controller;

import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.request.PostSaveDto;
import org.springframework.web.bind.annotation.GetMapping;
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
public class PostController {

	@GetMapping("/posts")
	public String getPosts() {
		return "Hello Mocadev Blog";
	}

	@PostMapping("/posts")
	public Map<String, String> savePost(@Valid @RequestBody PostSaveDto params) {
		log.info("params = {}", params);
		return Map.of();
	}

}

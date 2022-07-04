package me.mocadev.mocadevblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.request.PostSaveDto;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
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
	public Map<String, String> savePost(@Valid @RequestBody PostSaveDto params, Errors errors) {
		log.info("params = {}", params);
		if (errors.hasErrors()) {
			final List<FieldError> fieldErrors = errors.getFieldErrors();
			final FieldError fieldError = fieldErrors.get(0);
			final String field = fieldError.getField();
			final String errorMessage = fieldError.getDefaultMessage();

			Map<String, String> error = new HashMap<>();
			error.put(field, errorMessage);
			return error;
		}
		return Map.of();
	}

}

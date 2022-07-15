package me.mocadev.mocadevblog.controller;

import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.exception.MocadevException;
import me.mocadev.mocadevblog.exception.PostNotFoundException;
import me.mocadev.mocadevblog.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-05
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
		final ErrorResponse response = ErrorResponse.builder()
			.code("400")
			.message("잘못된 요청입니다.")
			.build();
		for (FieldError fieldError : e.getFieldErrors()) {
			response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return response;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(PostNotFoundException.class)
	@ResponseBody
	public ErrorResponse postNotFoundException(PostNotFoundException e) {
		return ErrorResponse.builder()
			.code("404")
			.message(e.getMessage())
			.build();
	}

	@ExceptionHandler(MocadevException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> mocadevException(MocadevException e) {
		final int statusCode = e.getStatusCode();
		final ErrorResponse response = ErrorResponse.builder()
			.code(String.valueOf(statusCode))
			.message(e.getMessage())
			.build();
		return ResponseEntity.status(statusCode)
			.body(response);
	}
}

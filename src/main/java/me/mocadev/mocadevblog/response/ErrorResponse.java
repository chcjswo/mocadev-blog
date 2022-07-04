package me.mocadev.mocadevblog.response;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 * 에러 반환
 *
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-05
 **/
@Getter
public class ErrorResponse {

	private final String code;
	private final String message;
	private final Map<String, String> validation = new HashMap<>();

	public void addValidation(String fieldName, String errorMessage) {
		this.validation.put(fieldName, errorMessage);
	}

	public ErrorResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
}

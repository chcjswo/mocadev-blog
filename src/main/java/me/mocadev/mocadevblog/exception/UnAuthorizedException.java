package me.mocadev.mocadevblog.exception;

import org.springframework.http.HttpStatus;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-06
 **/
public class UnAuthorizedException extends MocadevException {

	private static final String MESSAGE = "인증이 필요합니다.";

	public UnAuthorizedException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}
}

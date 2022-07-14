package me.mocadev.mocadevblog.exception;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-15
 **/
public class PostNotFoundException extends RuntimeException {

	private static final String MESSAGE = "존재하지 않는 글입니다.";

	public PostNotFoundException() {
		super(MESSAGE);
	}
}

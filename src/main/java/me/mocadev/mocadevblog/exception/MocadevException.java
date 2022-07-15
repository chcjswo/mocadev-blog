package me.mocadev.mocadevblog.exception;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-16
 **/
public abstract class MocadevException extends RuntimeException {

	public MocadevException(String message) {
		super(message);
	}

	public MocadevException(String message, Throwable cause) {
		super(message, cause);
	}

	public abstract int getStatusCode();
}

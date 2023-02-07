package me.mocadev.mocadevblog.exception;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-08
 **/
public class InvalidLogin extends MocadevException {

	private static final String MESSAGE = "아이디/비밀번호가 올바르지 않습니다.";

	public InvalidLogin() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return 400;
	}
}

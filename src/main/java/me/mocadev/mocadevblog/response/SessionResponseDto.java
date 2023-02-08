package me.mocadev.mocadevblog.response;

import lombok.Getter;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-09
 **/
@Getter
public class SessionResponseDto {

	private final String accessToken;

	public SessionResponseDto(String accessToken) {
		this.accessToken = accessToken;
	}
}

package me.mocadev.mocadevblog.response;

import lombok.Builder;
import lombok.Getter;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-10
 **/
@Getter
public class PostResponseDto {

	private final Long id;
	private final String title;
	private final String content;


	@Builder
	public PostResponseDto(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
}

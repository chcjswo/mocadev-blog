package me.mocadev.mocadevblog.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-15
 **/
@Getter
public class PostEditor {

	private final String title;
	private final String content;

	@Builder
	public PostEditor(String title, String content) {
		this.title = title;
		this.content = content;
	}
}

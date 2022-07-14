package me.mocadev.mocadevblog.request;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-14
 **/
@Getter
@Setter
@ToString
public class PostEditDto {

	@NotBlank(message = "제목은 필수입니다.")
	private final String title;

	@NotBlank(message = "내용은 필수입니다.")
	private final String content;

	@Builder
	public PostEditDto(String title, String content) {
		this.title = title;
		this.content = content;
	}

}

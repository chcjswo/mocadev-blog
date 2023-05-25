package me.mocadev.mocadevblog.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.mocadev.mocadevblog.exception.InvalidRequestException;

/**
 * 글 등록 DTO
 *
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-04
 **/
@Getter
@Setter
@ToString
public class PostSaveDto {

	@NotBlank(message = "제목은 필수입니다.")
	private final String title;

	@NotBlank(message = "내용은 필수입니다.")
	private final String content;

	@Builder
	public PostSaveDto(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void validate() {
		if (title.contains("바보")) {
			throw new InvalidRequestException();
		}
	}

}

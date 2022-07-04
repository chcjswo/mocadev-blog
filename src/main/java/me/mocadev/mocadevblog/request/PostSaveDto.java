package me.mocadev.mocadevblog.request;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * 글 등록 DTO
 *
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-04
 **/
@Data
@Builder
public class PostSaveDto {

	@NotBlank(message = "제목은 필수입니다.")
	private String title;

	@NotBlank(message = "내용은 필수입니다.")
	private String content;

}

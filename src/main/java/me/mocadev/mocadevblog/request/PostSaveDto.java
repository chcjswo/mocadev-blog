package me.mocadev.mocadevblog.request;

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
public class PostSaveDto {

	private String title;
	private String content;

}

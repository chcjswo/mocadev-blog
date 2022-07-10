package me.mocadev.mocadevblog.response;

import lombok.Builder;
import lombok.Getter;
import me.mocadev.mocadevblog.domain.Post;

/**
 * The type Post response dto.
 *
 * @author chcjswo
 * @version 1.0.0
 * @blog https ://mocadev.tistory.com
 * @github https ://github.com/chcjswo
 * @since 2022 -07-10
 */
@Getter
public class PostResponseDto {

	private final Long id;
	private final String title;
	private final String content;

	public PostResponseDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
	}

	/**
	 * Instantiates a new Post response dto.
	 *
	 * @param id the id
	 * @param title the title
	 * @param content the content
	 */
	@Builder
	public PostResponseDto(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
}

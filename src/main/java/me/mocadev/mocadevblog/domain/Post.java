package me.mocadev.mocadevblog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-08
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Lob
	private String content;

	@Builder
	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public PostEditor.PostEditorBuilder toEditor() {
		return PostEditor.builder()
			.title(title)
			.content(content);
	}

	public void edit(PostEditor postEditor) {
		this.title = postEditor.getTitle();
		this.content = postEditor.getContent();
	}
}

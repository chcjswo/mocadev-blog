package me.mocadev.mocadevblog.request;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-07
 **/
@Data
public class LoginDto {

	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;
	@NotBlank(message = "패스워드를 입력해주세요.")
	private String password;

	@Builder
	public LoginDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
}

package me.mocadev.mocadevblog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-03-24
 **/
@Data
@ConfigurationProperties(prefix = "mocadev")
public class AppConfig {

	private Mocadev data;

	@Data
	private static class Mocadev {
		private String name;
		private Integer age;
	}
}

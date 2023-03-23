package me.mocadev.mocadevblog;

import me.mocadev.mocadevblog.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class MocadevBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MocadevBlogApplication.class, args);
	}

}

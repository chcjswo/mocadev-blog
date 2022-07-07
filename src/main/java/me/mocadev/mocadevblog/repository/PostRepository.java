package me.mocadev.mocadevblog.repository;

import me.mocadev.mocadevblog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-08
 **/
public interface PostRepository extends JpaRepository<Post, Long> {

}

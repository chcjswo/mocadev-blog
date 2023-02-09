package me.mocadev.mocadevblog.repository;

import java.util.Optional;
import me.mocadev.mocadevblog.domain.Session;
import org.springframework.data.repository.CrudRepository;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-07
 **/
public interface SessionRepository extends CrudRepository<Session, Long> {

	Optional<Session> findByAccessToken(String accessToken);
}

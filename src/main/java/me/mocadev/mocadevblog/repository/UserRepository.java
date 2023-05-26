package me.mocadev.mocadevblog.repository;

import me.mocadev.mocadevblog.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-07
 **/
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);
}

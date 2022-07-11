package me.mocadev.mocadevblog.repository;

import java.util.List;
import me.mocadev.mocadevblog.domain.Post;
import me.mocadev.mocadevblog.request.PostSearch;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-12
 **/
public interface PostRepositoryCustom {

	List<Post> getList(PostSearch postSearch);

}

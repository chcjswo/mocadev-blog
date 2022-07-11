package me.mocadev.mocadevblog.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.mocadev.mocadevblog.domain.Post;
import me.mocadev.mocadevblog.domain.QPost;
import me.mocadev.mocadevblog.request.PostSearch;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2022-07-12
 **/
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Post> getList(PostSearch postSearch) {
		return jpaQueryFactory.selectFrom(QPost.post)
			.limit(postSearch.getSize())
			.offset(postSearch.getOffset())
			.orderBy(QPost.post.id.desc())
			.fetch();
	}
}

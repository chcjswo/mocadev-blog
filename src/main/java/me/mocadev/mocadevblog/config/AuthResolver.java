package me.mocadev.mocadevblog.config;

import lombok.RequiredArgsConstructor;
import me.mocadev.mocadevblog.domain.Session;
import me.mocadev.mocadevblog.exception.UnAuthorizedException;
import me.mocadev.mocadevblog.repository.SessionRepository;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-06
 **/
@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {

	private final SessionRepository sessionRepository;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(UserSession.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
		throws Exception {
		final String accessToken = webRequest.getHeader("Authorization");
		if (accessToken == null || accessToken.equals("")) {
			throw new UnAuthorizedException();
		}

		final Session session = sessionRepository.findByAccessToken(accessToken)
			.orElseThrow(UnAuthorizedException::new);

		return new UserSession(session.getUser().getId());
	}
}

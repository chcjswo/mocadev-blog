package me.mocadev.mocadevblog.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if (request == null) {
			log.error("HttpServletRequest is null");
			throw new UnAuthorizedException();
		}
		Cookie[] cookies = request.getCookies();
		if (cookies.length == 0) {
			log.error("쿠키 없음");
			throw new UnAuthorizedException();
		}
		String accessToken = cookies[0].getValue();

		final Session session = sessionRepository.findByAccessToken(accessToken)
			.orElseThrow(UnAuthorizedException::new);

		return new UserSession(session.getUser().getId());
	}
}

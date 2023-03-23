package me.mocadev.mocadevblog.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.exception.UnAuthorizedException;
import me.mocadev.mocadevblog.repository.SessionRepository;
import org.apache.tomcat.util.codec.binary.Base64;
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
	private static final String KEY = "s27bPAGI6zwnDIpvxE8cqcJLKCSpZel7cVg+JAVM43A=";
	private final AppConfig appConfig;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(UserSession.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		log.info(">>>>>>>>>>>> {}", appConfig.toString());
		final String jws = webRequest.getHeader("Authorization");
		if (jws == null || jws.equals("")) {
			throw new UnAuthorizedException();
		}
		byte[] decodedKey = Base64.decodeBase64(KEY);

		try {
			Jws<Claims> claims = Jwts.parserBuilder()
				.setSigningKey(decodedKey)
				.build()
				.parseClaimsJws(jws);
			String userId = claims.getBody().getSubject();
			return new UserSession(Long.parseLong(userId));
		} catch (JwtException e) {
			throw new UnAuthorizedException();
		}
	}
}

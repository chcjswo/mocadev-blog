package me.mocadev.mocadevblog.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.exception.UnAuthorizedException;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2023-02-06
 **/
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
							 Object handler) throws Exception {
		log.info(">> preHandle");
		final String accessToken = request.getParameter("accessToken");
		if (accessToken != null && !accessToken.equals("")) {
			request.setAttribute("userName", accessToken);
			return true;
		}
		throw new UnAuthorizedException();
	}
}

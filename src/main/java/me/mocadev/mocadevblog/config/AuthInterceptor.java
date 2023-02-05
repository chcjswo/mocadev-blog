package me.mocadev.mocadevblog.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import me.mocadev.mocadevblog.exception.UnAuthorizedException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
		if (accessToken != null && accessToken.equals("chcjswo")) {
			return true;
		}
		throw new UnAuthorizedException();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
		log.info(">> postHandle");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
								Object handler, Exception ex) throws Exception {
		log.info(">> afterCompletion");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}

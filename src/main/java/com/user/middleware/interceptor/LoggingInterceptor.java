package com.user.middleware.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * LoggingInterceptor
 */
public class LoggingInterceptor extends HandlerInterceptorAdapter {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    super.afterCompletion(request, response, handler, ex);

    StringBuilder logMessage = new StringBuilder();
    logMessage.append("Method: ").append("[" + request.getMethod() + "]").append(", ");
    logMessage.append("URI: ").append("[" + request.getRequestURI() + "]").append(", ");
    logMessage.append("Status: ").append("[" + response.getStatus() + "]").append(", ");
    logMessage.append("Remote Address: ").append("[" + request.getRemoteAddr() + "]").append(", ");

    if (ex != null) {
      logger.error(logMessage.toString(), ex);
    } else {
      logger.info(logMessage.toString());
    }
  }
}
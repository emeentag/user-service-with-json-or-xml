package com.user.middleware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ConstraintsViolationException
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ConstraintsViolationException extends Exception {

  private static final long serialVersionUID = 1L;

  public ConstraintsViolationException(String message) {
    super(message);
  }
}
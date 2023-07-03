package com.chatgptgoestoschool.recipediscovery.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.chatgptgoestoschool.recipediscovery.exception.JSONHandlingException;
import com.chatgptgoestoschool.recipediscovery.exception.RecipeNotFoundException;
import com.chatgptgoestoschool.recipediscovery.exception.RecipeNotOwnedException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler({JSONHandlingException.class, RecipeNotFoundException.class,
      RecipeNotOwnedException.class})
  public ResponseEntity<Object> handleJSONHandlingException(JSONHandlingException ex,
      WebRequest request) {
    return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), ex.getStatus());
  }
}

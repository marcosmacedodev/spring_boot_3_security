package com.example.demo.services.exceptions;

//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ServiceErrorHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler({ AccessDeniedException.class })
        public ResponseEntity<Object> handleAccessDeniedException(
          Exception ex, HttpServletRequest request/* , WebRequest webRequest*/) {
            ResponseError responseError = new ResponseError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseError);
        }

        @ExceptionHandler({ UsuarioException.class })
        public ResponseEntity<Object> handleUsuarioException(
          Exception ex, HttpServletRequest request) {
            ResponseError responseError = new ResponseError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
        }


}

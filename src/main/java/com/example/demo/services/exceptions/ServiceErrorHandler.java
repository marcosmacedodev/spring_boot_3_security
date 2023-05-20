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
            return responseEntity(HttpStatus.FORBIDDEN, ex.getMessage(), request.getRequestURI());
        }

        @ExceptionHandler({ UserRegisterException.class })
        public ResponseEntity<Object> handleUserRegisterException(
          Exception ex, HttpServletRequest request) {
            return responseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        }

        @ExceptionHandler({ UserNotFoundException.class })
        public ResponseEntity<Object> handleUserNotFoundException(
          Exception ex, HttpServletRequest request) {
            return responseEntity(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
        }

        private ResponseEntity<Object> responseEntity(HttpStatus httpStatus, String message, String uri){
          ResponseError responseError = new ResponseError(System.currentTimeMillis(), httpStatus.value(), httpStatus.getReasonPhrase(), message, uri);
            return ResponseEntity.status(httpStatus).body(responseError);
        }

}

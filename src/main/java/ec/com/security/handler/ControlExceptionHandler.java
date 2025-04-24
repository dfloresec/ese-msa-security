package ec.com.security.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ec.com.security.exception.AuthenticationFailedException;
import ec.com.security.exception.TokenFailedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class ControlExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	private ResponseEntity<Object> handlerException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()).body("ERROR INTERNO");
	}

	@ExceptionHandler(AuthenticationFailedException.class)
	private ResponseEntity<Object> handlerAuthenticationFailedException(AuthenticationFailedException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()).body(ex.getMessage());
	}
	
	@ExceptionHandler(TokenFailedException.class)
	private ResponseEntity<Object> handlerTokenFailedException(TokenFailedException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()).body(ex.getMessage());
	}
	
	
}

package ec.com.security.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import ec.com.security.service.AuthenticationService;
import ec.com.security.service.dto.RequestTokenDto;

@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest {

	@InjectMocks
	private AuthenticationController authenticationController;

	@Mock
	private AuthenticationService authenticationService;

	@Test
	void testGenerateToken() {
		RequestTokenDto mockRequest = new RequestTokenDto();
		mockRequest.setUser("client");
		mockRequest.setPassword("client123");
		mockRequest.setClientSecret("secret");

		Map<String, String> mockResponse = new HashMap<>();
		mockResponse.put("token", "abc123");

		when(authenticationService.generateToken(mockRequest)).thenReturn(mockResponse);

		Map<String, String> result = authenticationController.generateToken(mockRequest);

		assertNotNull(result);
		assertEquals("abc123", result.get("token"));
		verify(authenticationService, times(1)).generateToken(mockRequest);
	}

	@Test
	void testValidateToken() {
		String bearerToken = "Bearer abc123";
		String clientSecret = "secret";

		doNothing().when(authenticationService).validateToken(bearerToken, clientSecret);

		assertDoesNotThrow(() -> authenticationController.validateToken(bearerToken, clientSecret));

		verify(authenticationService, times(1)).validateToken(bearerToken, clientSecret);
	}

	@Test
	void testValidateTokenThrowsException() {
		String bearerToken = "Bearer abc123";
		String clientSecret = "wrongSecret";

		doThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token or secret"))
				.when(authenticationService).validateToken(bearerToken, clientSecret);

		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> authenticationController.validateToken(bearerToken, clientSecret));

		assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
		assertEquals("Invalid token or secret", exception.getReason());
		verify(authenticationService, times(1)).validateToken(bearerToken, clientSecret);
	}
}

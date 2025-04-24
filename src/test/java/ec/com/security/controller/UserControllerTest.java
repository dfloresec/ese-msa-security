package ec.com.security.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import ec.com.security.service.UserService;
import ec.com.security.service.dto.UserDto;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@Test
	void testCreateUser() {
		UserDto mockUser = new UserDto();
		mockUser.setUsermane("jdoe");
		mockUser.setEmail("jdoe@gmail.com");
		mockUser.setPassword("12345");

		doNothing().when(userService).saveUser(any());

		assertDoesNotThrow(() -> userController.create(mockUser));

		verify(userService, times(1)).saveUser(mockUser);
	}

	@Test
	void testCreateUserThrowsException() {
		UserDto mockUser = new UserDto();
		mockUser.setUsermane("jdoe");

		doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user")).when(userService)
				.saveUser(mockUser);

		var exception = assertThrows(ResponseStatusException.class, () -> userController.create(mockUser));

		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
		assertEquals("Invalid user", exception.getReason());
		verify(userService, times(1)).saveUser(mockUser);
	}
}

package ec.com.security.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ec.com.security.domain.UsersEntity;
import ec.com.security.repository.UserRepository;
import ec.com.security.service.dto.UserDto;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Test
	void givenUserWhenGetUserByIdThenReturnUsername() {
		String username = "dfloresg";

		UsersEntity usersEntity = new UsersEntity();
		usersEntity.setUsermane(username);
		when(userRepository.findById(anyString())).thenReturn(Optional.of(usersEntity));

		UserDto user = userService.getUser(username);
		assertEquals(username, user.getUsermane());
	}

}

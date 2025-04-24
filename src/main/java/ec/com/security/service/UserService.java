package ec.com.security.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ec.com.security.domain.UsersEntity;
import ec.com.security.repository.UserRepository;
import ec.com.security.service.dto.UserDto;
import ec.com.security.service.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public void saveUser(UserDto userDto) {
		UsersEntity users = UsersMapper.INSTANCE.toUsersEntity(userDto);
		userRepository.save(users);
	}
	
	public UserDto getUser(String usermane) {
		Optional<UsersEntity> user = userRepository.findById(usermane);
		if(user.isPresent()) {
			return UsersMapper.INSTANCE.toUserDto(user.get());
		}
		return null;
	}

}

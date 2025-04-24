package ec.com.security.service.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ToString
public class UserDto {
	String usermane;

	String email;

	String password;
}

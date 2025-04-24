package ec.com.security.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "USERS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UsersEntity {

	@Id
	String usermane;

	String email;

	String password;

}

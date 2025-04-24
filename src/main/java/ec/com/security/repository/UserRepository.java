package ec.com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.security.domain.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String> {

}

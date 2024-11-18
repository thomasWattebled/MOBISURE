package mobisure.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mobisure.project.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	List<User> findAll();
	
	Optional<User> findById(Long id);
	
}

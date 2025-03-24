package mobisure.project.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mobisure.project.entity.User;

/**
 * Repository interface for managing User entities.
 */
public interface UserRepository extends JpaRepository<User,Long> {

	/**
     * Retrieve all User entities from the database.
     *
     * @return a List of all User entities.
     */
	List<User> findAll();
	
	/**
     * Find a User entity by its ID.
     *
     * @param id the ID of the User to retrieve.
     * @return an Optional containing the User if found, or empty if not found.
     */
	Optional<User> findById(Long id);
	
	/**
     * Find a User entity by its email address.
     *
     * @param mail the email address of the User to retrieve.
     * @return an Optional containing the User if found, or empty if not found.
     */
	Optional<User> findByMail(String mail);
	
	Optional<User> findByMailAndDateNaissance(String mail,Date date);
}

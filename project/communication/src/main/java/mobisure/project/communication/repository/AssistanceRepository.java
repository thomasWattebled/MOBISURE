package mobisure.project.communication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mobisure.project.communication.entity.Assistance;

public interface AssistanceRepository extends JpaRepository<Assistance,Long> {

	Optional<Assistance> findById(Long id);
	
	List<Assistance> findByIdClient(Long idClient);
	
}

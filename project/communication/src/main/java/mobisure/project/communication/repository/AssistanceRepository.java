package mobisure.project.communication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mobisure.project.communication.entity.Assistance;

public interface AssistanceRepository extends JpaRepository<Assistance,Long> {

	Optional<Assistance> findById(Long id);
	
	List<Assistance> findByIdClient(Long idClient);
	
	Assistance findByNumDossier(String numDossier);

	@Query("SELECT a FROM Assistance a WHERE (a.gerer = false)")
	List<Assistance> findDisponnible();
}

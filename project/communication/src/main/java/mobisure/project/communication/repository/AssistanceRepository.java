package mobisure.project.communication.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;

import mobisure.project.communication.entity.Assistance;

public interface AssistanceRepository extends MongoRepository<Assistance,String> {

	Optional<Assistance> findById(String id);
	
	List<Assistance> findByIdClient(Long idClient);
	
	Assistance findByNumDossier(String numDossier);

	List<Assistance> findByGererFalse();
}

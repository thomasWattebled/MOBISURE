package mobisure.project.sinistre.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;

import mobisure.project.sinistre.document.HealthDocument;

public interface HealthRepository extends MongoRepository<HealthDocument, String> {
	public List<HealthDocument> findBySinistreId(Long sinistreId);
}

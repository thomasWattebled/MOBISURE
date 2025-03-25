package mobisure.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import mobisure.project.document.HealthDocument;

public interface HealthRepository extends MongoRepository<HealthDocument, String> {
	public List<HealthDocument> findBySinistreId(Long sinistreId);
}

package mobisure.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import mobisure.project.document.CarDocument;

public interface CarRepository extends MongoRepository<CarDocument, String> {
	
	List<CarDocument> findBySinistreId(Long sinistreId);
}

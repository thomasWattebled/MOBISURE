package mobisure.project.sinistre.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;

import mobisure.project.sinistre.document.CarDocument;

public interface CarRepository extends MongoRepository<CarDocument, String> {

	List<CarDocument> findBySinistreId(Long sinistreId);
}

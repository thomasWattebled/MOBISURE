package mobisure.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import mobisure.project.document.FileDocument;

public interface FileRepository extends MongoRepository<FileDocument, String> {
	
	List<FileDocument> findBySinistreId (Long sinistreId);
}

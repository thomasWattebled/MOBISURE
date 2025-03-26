package mobisure.project.sinistre.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;

import mobisure.project.sinistre.document.FileDocument;

public interface FileRepository extends MongoRepository<FileDocument, String> {

	List<FileDocument> findBySinistreId(Long sinistreId);
}

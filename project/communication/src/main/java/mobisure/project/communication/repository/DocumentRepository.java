package mobisure.project.communication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mobisure.project.communication.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findByNumDossier(String numDossier);
	
}

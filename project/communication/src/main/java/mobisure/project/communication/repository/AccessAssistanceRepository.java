package mobisure.project.communication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mobisure.project.communication.entity.AccessAssistance;

public interface AccessAssistanceRepository extends JpaRepository<AccessAssistance,Long> {

	List<AccessAssistance> findByIdAssistance(String idAssistance);
	
	List<AccessAssistance> findByIdUser(Long idUser);

	AccessAssistance findByIdAssistanceAndIdUser(String idAssistance, Long idUser);

	
}

package mobisure.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mobisure.project.entity.Sinistre;

public interface SinistreRepository extends JpaRepository<Sinistre, Long> {
	
	Optional<Sinistre> findById(Long id);
	
	List<Sinistre> findByUserId(Long userId);
	
	List<Sinistre> findByContractId(Long contractId);
}

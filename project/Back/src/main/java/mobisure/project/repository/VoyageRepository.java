package mobisure.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mobisure.project.entity.Voyage;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {
}

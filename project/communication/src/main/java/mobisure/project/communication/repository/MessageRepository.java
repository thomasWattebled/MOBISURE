package mobisure.project.communication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mobisure.project.communication.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByReceveurId(Long receveurId);
	
	List<Message> findByExpediteurIdAndReceveurId(Long expediteurId, Long receveurId);
	
	@Query("SELECT m FROM Message m WHERE (m.expediteurId = :user1 AND m.receveurId = :user2) OR (m.expediteurId = :user2 AND m.receveurId = :user1)")
	List<Message> findConversationMessages(@Param("user1") Long user1, @Param("user2") Long user2);

}

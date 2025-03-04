package mobisure.project.communication.service;

import java.util.List;

import mobisure.project.communication.entity.Message;

public interface MessageService {

	void sendMessage(Long expediteurId, Long receveurId, String contenu);
	
	List<Message> getReceveurMessage(Long receveurId);
	
	List<Message> getConversationMessage(Long expediteurId, Long receveurId);
	
	boolean checkConversationExists(Long expediteurId, Long receveurId);
	
	Message createConversation(Long expediteurId, Long receveurId, String contenue);
	
	List<Message> getUserMessages(Long userId);
	
	List<Message> getAllMessage();
	
}

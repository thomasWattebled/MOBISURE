package mobisure.project.communication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.communication.entity.Message;
import mobisure.project.communication.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository repo;

	@Override
	public void sendMessage(Long expediteurId, Long receveurId, String contenu) {
		Message message = new Message(expediteurId,receveurId,contenu);
		repo.save(message);
	}

	@Override
	public List<Message> getReceveurMessage(Long receveurId) {
		return repo.findByReceveurId(receveurId);
	}

	@Override
	public List<Message> getConversationMessage(Long expediteurId, Long receveurId) {
		return repo.findConversationMessages(expediteurId, receveurId);
	}
	
}

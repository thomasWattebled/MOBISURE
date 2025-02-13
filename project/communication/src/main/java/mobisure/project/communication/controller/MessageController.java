package mobisure.project.communication.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import mobisure.project.communication.entity.Message;
import mobisure.project.communication.request.MessageRequest;
import mobisure.project.communication.service.MessageService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class MessageController {
	
	@Autowired
    private SimpMessagingTemplate template;

	@Autowired
	private MessageService service;
	
	@PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody MessageRequest request) {
        Message message = new Message(request.getExpediteurId(), request.getReceveurId(), request.getContenu());
        service.sendMessage(message.getExpediteurId(), message.getReceveurId(), message.getContenu());
        
        template.convertAndSend("/topic/messages/" + request.getReceveurId(), message);
        return ResponseEntity.ok("Message envoyé avec succès");
    }
	
	@GetMapping("/MyMessage/{receveurId}")
    public List<Message> getMyMessage(@PathVariable Long receveurId) {
        return service.getReceveurMessage(receveurId);
    }

    @PostMapping("/Conversation")
    public List<Message> getConversation(@RequestBody MessageRequest request) {
        return service.getConversationMessage(request.getExpediteurId(), request.getReceveurId());
    }
	
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessageViaWebSocket(Message message) {
        service.sendMessage(message.getExpediteurId(), message.getReceveurId(), message.getContenu());
        return message;
    }
	
}

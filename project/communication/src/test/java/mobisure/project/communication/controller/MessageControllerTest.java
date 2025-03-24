package mobisure.project.communication.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import mobisure.project.communication.entity.Message;
import mobisure.project.communication.request.MessageRequest;
import mobisure.project.communication.service.MessageService;

public class MessageControllerTest {


    @Mock
    private MessageService service;

    @Mock
    private SimpMessagingTemplate template;

    @InjectMocks
    private MessageController controller;

    private Message message1;
    private Message message2;
	
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        message1 = new Message(1L, 2L, "Bonjour");
        message2 = new Message(2L, 1L, "Salut");
    }
    
    @Test
    void testSendMessage() {
    	//Création d'un objet MessageRequest
        MessageRequest request = new MessageRequest();
        request.setExpediteurId(1L);
        request.setReceveurId(2L);
        request.setContenu("Bonjour");
        doNothing().when(service).sendMessage(anyLong(), anyLong(), anyString());
        doNothing().when(template).convertAndSend(anyString(), any(Message.class));
        ResponseEntity<String> response = controller.send(request);
        assertEquals(200, response.getStatusCodeValue());
        verify(service, times(1)).sendMessage(eq(1L), eq(2L), eq("Bonjour"));
    }
    
    @Test
    void testGetMyMessage() {
        List<Message> messages = Arrays.asList(message1,message2);
        when(service.getUserMessages(2L)).thenReturn(messages);
        List<Message> result = controller.getMyMessage(2L);
        assertEquals(2, result.size());
        assertEquals("Bonjour", result.get(0).getContenu());
        assertEquals("Salut", result.get(1).getContenu());
    }
    
    @Test
    void testGetConversation() {
    	//Création d'un objet MessageRequest
        MessageRequest request = new MessageRequest();
        request.setExpediteurId(1L);
        request.setReceveurId(2L);
        request.setContenu("Bonjour");
        
        List<Message> messages = Arrays.asList(message1,message2);
        when(service.getConversationMessage(1L, 2L)).thenReturn(messages);
        List<Message> result = controller.getConversation(request);
        assertEquals(2, result.size());
        assertEquals("Bonjour", result.get(0).getContenu());
        assertEquals("Salut", result.get(1).getContenu());
    }
    
    @Test
    void testSendMessageViaWebSocket() {
        doNothing().when(service).sendMessage(anyLong(), anyLong(), anyString());
        Message result = controller.sendMessageViaWebSocket(message1);
        assertEquals("Bonjour", result.getContenu());
        verify(service, times(1)).sendMessage(eq(1L), eq(2L), eq("Bonjour"));
    }
    
    @Test
    void testCheckConversation_Exists() {
        when(service.checkConversationExists(1L, 2L)).thenReturn(true);
        ResponseEntity<Boolean> response = controller.checkConversationExists(1L, 2L);
        assertTrue(response.getBody());
    }
    
    @Test
    void testCheckConversation_NoExists() {
        when(service.checkConversationExists(1L, 2L)).thenReturn(false);
        ResponseEntity<Boolean> response = controller.checkConversationExists(1L, 2L);
        assertFalse(response.getBody());
    }
    
    @Test
    void testCreateConversation() {
    	//Création d'un objet MessageRequest
        MessageRequest request = new MessageRequest();
        request.setExpediteurId(1L);
        request.setReceveurId(2L);
        request.setContenu("Bonjour");
        
        when(service.createConversation(anyLong(), anyLong(), anyString())).thenReturn(message1);
        ResponseEntity<Message> response = controller.createConversation(request);
        assertEquals("Bonjour", response.getBody().getContenu());
    }
    
    @Test
    void testAllMessages() {
        List<Message> messages = Arrays.asList(message1,message2);
        when(service.getAllMessage()).thenReturn(messages);
        List<Message> result = controller.all();
        assertEquals(2, result.size());
        assertEquals("Bonjour", result.get(0).getContenu());
        assertEquals("Salut", result.get(1).getContenu());
    }
    
}

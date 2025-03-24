package mobisure.project.communication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mobisure.project.communication.entity.Message;
import mobisure.project.communication.repository.MessageRepository;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {

	@Mock
	private MessageRepository messageRepository;
	
	@InjectMocks
	private MessageServiceImpl messageService; 
	
	private Message message1;
	private Message message2;
	
	@BeforeEach
	public void setUp() {
		message1 = new Message(1L,2L,"Bonjour");
		message2 = new Message(2L, 1L, "Salut");
	}
	
	@Test
    public void testSendMessage() {
        when(messageRepository.save(any(Message.class))).thenReturn(message1);
        messageService.sendMessage(1L, 2L, "Bonjour");
        verify(messageRepository, times(1)).save(any(Message.class));
    }
	
	@Test
    public void testGetReceveurMessage() {
        when(messageRepository.findByReceveurId(2L)).thenReturn(Arrays.asList(message1));
        List<Message> result = messageService.getReceveurMessage(2L);
        assertEquals(1, result.size());
        assertEquals("Bonjour", result.get(0).getContenu());
        verify(messageRepository, times(1)).findByReceveurId(2L);
    }
	
	@Test
    public void testGetConversationMessage() {
        when(messageRepository.findConversationMessages(1L, 2L)).thenReturn(Arrays.asList(message1, message2));

        List<Message> result = messageService.getConversationMessage(1L, 2L);

        assertEquals(2, result.size());
        assertEquals("Bonjour", result.get(0).getContenu());
        assertEquals("Salut", result.get(1).getContenu());
        verify(messageRepository, times(1)).findConversationMessages(1L, 2L);
    }
	
	@Test
    public void testCheckConversationExists_True() {
        when(messageRepository.findConversation(1L, 2L)).thenReturn(Arrays.asList(message1));
        boolean exists = messageService.checkConversationExists(1L, 2L);
        assertTrue(exists);
        verify(messageRepository, times(1)).findConversation(1L, 2L);
    }
	
	@Test
    public void testCheckConversationExists_False() {
        when(messageRepository.findConversation(1L, 2L)).thenReturn(Arrays.asList());
        boolean exists = messageService.checkConversationExists(1L, 2L);
        assertFalse(exists);
        verify(messageRepository, times(1)).findConversation(1L, 2L);
    }
	
	@Test
    public void testCreateConversation() {
        when(messageRepository.save(any(Message.class))).thenReturn(message1);
        Message result = messageService.createConversation(1L, 2L, "Bonjour");
        assertNotNull(result);
        assertEquals("Bonjour", result.getContenu());
        verify(messageRepository, times(1)).save(any(Message.class));
    }
	
	@Test
    public void testGetUserMessages() {
        when(messageRepository.findByExpediteurIdOrReceveurId(1L, 1L)).thenReturn(Arrays.asList(message1, message2));
        List<Message> result = messageService.getUserMessages(1L);
        assertEquals(2, result.size());
        verify(messageRepository, times(1)).findByExpediteurIdOrReceveurId(1L, 1L);
    }

	@Test
    public void testGetAllMessage() {
        List<Message> mockMessages = Arrays.asList(message1, message2);
		when(messageRepository.findAll()).thenReturn(mockMessages);

        List<Message> result = messageService.getAllMessage();

        assertEquals(2, result.size());
        assertEquals(mockMessages, result);
        verify(messageRepository, times(1)).findAll();
    }
	
	
}

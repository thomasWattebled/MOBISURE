package mobisure.project.communication.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageRequestTest {

	private MessageRequest messageRequest;

    @BeforeEach
    public void setUp() {
        messageRequest = new MessageRequest();
    }
    
    @Test
    public void testGetAndSetExpediteurId() {
        Long expediteurId = 123L;
        messageRequest.setExpediteurId(expediteurId);
        assertEquals(expediteurId, messageRequest.getExpediteurId());
    }
	
    @Test
    public void testGetAndSetReceveurId() {
        Long receveurId = 456L;
        messageRequest.setReceveurId(receveurId);
        assertEquals(receveurId, messageRequest.getReceveurId());
    }
    
    @Test
    public void testGetAndSetContenu() {
        String contenu = "Message test";
        messageRequest.setContenu(contenu);
        assertEquals(contenu, messageRequest.getContenu());
    }
    
    @Test
    public void testMessageRequestConstructor() {
        assertNotNull(messageRequest);
    }
    
}

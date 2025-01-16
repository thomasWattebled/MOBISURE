package mobisure.project.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class changeMdpRequestTest {

	private changeMdpRequest request;
	
	@BeforeEach
	public void setUp() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("12/08/1990");
		
		request = new changeMdpRequest();
		request.setMail("benj@gmail.com");
		request.setMdp("mdp");
		request.setDate(date);
	}
	
	@Test
	void testMail() {
		assertEquals("benj@gmail.com",request.getMail());
		request.setMail("mail");
		assertEquals("mail",request.getMail());
	}
	
	@Test
	void testDate() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("12/08/1990");
		Date date2 = dateFormat.parse("13/08/1990");
		
		assertEquals(date,request.getDate());
		request.setDate(date2);
		assertEquals(date2,request.getDate());
		
	}
	
	@Test
	void testMdp() {
		assertEquals("mdp",request.getMdp());
		request.setMdp("test");
		assertEquals("test",request.getMdp());
	}
	
}

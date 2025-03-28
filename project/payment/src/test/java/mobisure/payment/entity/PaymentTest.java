package mobisure.payment.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class PaymentTest {

    @Test
    void testId() {
        Payment payment = new Payment();
        payment.setId(1L);
        assertEquals(1L, payment.getId());
    }

    @Test
    void testCardNumber() {
        Payment payment = new Payment();
        payment.setCardNumber("1234567890123456");
        assertEquals("1234567890123456", payment.getCardNumber());
    }

    @Test
    void testCardHolder() {
        Payment payment = new Payment();
        payment.setCardHolder("John Doe");
        assertEquals("John Doe", payment.getCardHolder());
    }

    @Test
    void testCvv() {
        Payment payment = new Payment();
        payment.setCvv("123");
        assertEquals("123", payment.getCvv());
    }

    @Test
    void testAmount() {
        Payment payment = new Payment();
        payment.setAmount(100.50);
        assertEquals(100.50, payment.getAmount());
    }

    @Test
    void testPaymentDate() {
        Payment payment = new Payment();
        LocalDateTime dateTime = LocalDateTime.now();
        payment.setPaymentDate(dateTime);
        assertEquals(dateTime, payment.getPaymentDate());
    }

    @Test
    void testPaymentSuccess() {
        Payment payment = new Payment();
        payment.setPaymentSuccess(true);
        assertTrue(payment.isPaymentSuccess());
        
        payment.setPaymentSuccess(false);
        assertFalse(payment.isPaymentSuccess());
    }
}


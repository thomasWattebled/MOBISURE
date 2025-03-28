package mobisure.payment.request;

import org.junit.jupiter.api.Test;

import mobisure.project.payment.request.PaymentRequest;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRequestTest {

    @Test
    void testCardNumber() {
        PaymentRequest paymentRequest = new PaymentRequest();
        String cardNumber = "1234567812345670";
        paymentRequest.setCardNumber(cardNumber);
        assertEquals(cardNumber, paymentRequest.getCardNumber());
    }

    @Test
    void testCardHolder() {
        PaymentRequest paymentRequest = new PaymentRequest();
        String cardHolder = "John Doe";
        paymentRequest.setCardHolder(cardHolder);
        assertEquals(cardHolder, paymentRequest.getCardHolder());
    }

    @Test
    void testCvv() {
        PaymentRequest paymentRequest = new PaymentRequest();
        String cvv = "123";
        paymentRequest.setCvv(cvv);
        assertEquals(cvv, paymentRequest.getCvv());
    }

    @Test
    void testAmount() {
        PaymentRequest paymentRequest = new PaymentRequest();
        double amount = 100.50;
        paymentRequest.setAmount(amount);
        assertEquals(amount, paymentRequest.getAmount());
    }

    @Test
    void testSettersAndGetters() {
        PaymentRequest paymentRequest = new PaymentRequest();
        
        // Test card number
        String cardNumber = "1234567812345670";
        paymentRequest.setCardNumber(cardNumber);
        assertEquals(cardNumber, paymentRequest.getCardNumber());

        // Test card holder
        String cardHolder = "John Doe";
        paymentRequest.setCardHolder(cardHolder);
        assertEquals(cardHolder, paymentRequest.getCardHolder());

        // Test CVV
        String cvv = "123";
        paymentRequest.setCvv(cvv);
        assertEquals(cvv, paymentRequest.getCvv());

        // Test amount
        double amount = 100.50;
        paymentRequest.setAmount(amount);
        assertEquals(amount, paymentRequest.getAmount());
    }
}

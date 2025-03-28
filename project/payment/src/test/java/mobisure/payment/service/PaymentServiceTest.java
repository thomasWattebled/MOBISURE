package mobisure.payment.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private final PaymentService paymentService = new PaymentService();

    @Test
    void testProcessPayment_Success() {
        String cardNumber = "1234567812345670";  // Valide
        String cardHolder = "John Doe";
        String cvv = "123";  // Valide
        double amount = 100.0;

        String result = paymentService.processPayment(cardNumber, cardHolder, cvv, amount);
        assertEquals("Payment successful!", result);
    }

    @Test
    void testProcessPayment_InvalidCardNumber() {
        String cardNumber = "1234567812345671"; // Invalide
        String cardHolder = "John Doe";
        String cvv = "123";  // Valide
        double amount = 100.0;

        String result = paymentService.processPayment(cardNumber, cardHolder, cvv, amount);
        assertEquals("Invalid card number", result);
    }

    @Test
    void testProcessPayment_InvalidCVV() {
        String cardNumber = "1234567812345670";  // Valide
        String cardHolder = "John Doe";
        String cvv = "12";  // Invalide (trop court)
        double amount = 100.0;

        String result = paymentService.processPayment(cardNumber, cardHolder, cvv, amount);
        assertEquals("Invalid CVV", result);
    }

    @Test
    void testProcessPayment_FailureAmountZero() {
        String cardNumber = "1234567812345670";  // Valide
        String cardHolder = "John Doe";
        String cvv = "123";  // Valide
        double amount = 0.0;  // Montant à 0

        String result = paymentService.processPayment(cardNumber, cardHolder, cvv, amount);
        assertEquals("Payment failed!", result);
    }

    @Test
    void testProcessPayment_FailureNegativeAmount() {
        String cardNumber = "1234567812345670";  // Valide
        String cardHolder = "John Doe";
        String cvv = "123";  // Valide
        double amount = -100.0;  // Montant négatif

        String result = paymentService.processPayment(cardNumber, cardHolder, cvv, amount);
        assertEquals("Payment failed!", result);
    }
}


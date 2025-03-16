package mobisure.project.payment.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.payment.entity.Payment;

import java.time.LocalDateTime;

@Service
public class PaymentService {


    private boolean validateCardNumber(String cardNumber) {
        int sum = 0;
        for (int i = 0; i < cardNumber.length(); i++) {
            int digit = Integer.parseInt(cardNumber.substring(i, i + 1));
            if ((cardNumber.length() - i) % 2 == 0) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
        }
        return sum % 10 == 0;
    }

    private boolean validateCVV(String cvv) {
        return cvv.matches("\\d{3,4}");
    }

    public String processPayment(String cardNumber, String cardHolder, String cvv, double amount) {
        if (!validateCardNumber(cardNumber)) {
            return "Invalid card number";
        }

        if (!validateCVV(cvv)) {
            return "Invalid CVV";
        }
        boolean paymentSuccess = amount > 0;

        Payment payment = new Payment();
        payment.setCardNumber(cardNumber);
        payment.setCardHolder(cardHolder);
        payment.setCvv(cvv);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentSuccess(paymentSuccess);


        // Retourner un message de succès ou d'échec
        return paymentSuccess ? "Payment successful!" : "Payment failed!";
    }
}

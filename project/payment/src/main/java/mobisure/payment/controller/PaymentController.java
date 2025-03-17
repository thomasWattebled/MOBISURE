package mobisure.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mobisure.payment.service.PaymentService;
import mobisure.project.payment.request.PaymentRequest;

@RestController
@RequestMapping("/api") // Chemin de base pour toutes les routes du contrôleur
public class PaymentController {
	
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/tryPayment") // Gère les requêtes POST vers /api/tryPayment
    public String tryPayment(@RequestBody PaymentRequest paymentRequest) {
    	 return paymentService.processPayment(
 		        paymentRequest.getCardNumber(),
 		        paymentRequest.getCardHolder(),
 		        paymentRequest.getCvv(),
 		        paymentRequest.getAmount()
 		    );
    }
}
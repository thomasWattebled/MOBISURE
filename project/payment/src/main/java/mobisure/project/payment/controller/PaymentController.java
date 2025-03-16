package mobisure.project.payment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mobisure.project.payment.request.PaymentRequest;
import mobisure.project.payment.service.PaymentService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RestController

public class PaymentController {

    @Autowired
    private PaymentService paymentService;

		
    @PostMapping("/payment")
    public String simulatePayment(@RequestBody PaymentRequest paymentRequest) {
    	 return paymentService.processPayment(
    		        paymentRequest.getCardNumber(),
    		        paymentRequest.getCardHolder(),
    		        paymentRequest.getCvv(),
    		        paymentRequest.getAmount()
    		    );
    }


}

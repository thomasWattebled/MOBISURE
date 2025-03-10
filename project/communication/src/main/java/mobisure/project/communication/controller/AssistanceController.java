package mobisure.project.communication.controller;

import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.TypeAssistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.request.AssistanceRequest;
import mobisure.project.communication.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class AssistanceController {

    @Autowired
    private AssistanceService service;

    // Récupérer toutes les demandes d'assistance
    @GetMapping("/assistance/all")
    public List<Assistance> getAllAssistance() {
        return service.getAllAssistance();
    }
    
    @PutMapping("/assistance/updateStatus/{id}")
    public void updateStatus(@PathVariable Long id, @RequestBody Map<String, String> requestBody){
    	
    	String request_status = requestBody.get("status");
    	Status status = Status.valueOf(request_status);
    	
    	service.updateAssistance(id, status);
    }
    
    @GetMapping("/assistance/getMyAssistance/{id}")
    public List<Assistance> getMyAssistance(@PathVariable Long id){
    	List<Assistance> test = service.getMyAssistance(id);
    	return service.getMyAssistance(id);
    }

    // Ajouter une nouvelle demande d'assistance
    @PostMapping("/assistance/add")
    public ResponseEntity<String> addAssistance(@RequestBody AssistanceRequest assistanceRequest) {
        try {
            // Conversion de l'énumération
            TypeAssistance typeAssistance = TypeAssistance.valueOf(assistanceRequest.getType());
            Status status = Status.valueOf(assistanceRequest.getStatus());
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(assistanceRequest.getDate());

            // Créer une nouvelle demande d'assistance
            service.createAssistance(
                assistanceRequest.getId_client(),
                status,
                date,
                assistanceRequest.getMessage(),
                typeAssistance,
                assistanceRequest.getNom(),
                assistanceRequest.getPrenom(),
                assistanceRequest.getMail(),
                assistanceRequest.getMdp(),
                assistanceRequest.getTelephone()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body("Demande d'assistance ajoutée avec succès");

        } catch (IllegalArgumentException e) {
            // Gestion de l'erreur si le type d'assistance est invalide
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type d'assistance invalide");
        } catch (Exception e) {
            // Gestion des autres erreurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de la demande d'assistance");
        }
    }
}

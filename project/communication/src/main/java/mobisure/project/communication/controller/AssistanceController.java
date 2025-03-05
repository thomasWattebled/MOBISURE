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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
                typeAssistance
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

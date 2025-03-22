package mobisure.project.controller;

import mobisure.project.entity.Voyage;
import mobisure.project.service.DevisService;
import mobisure.project.repository.VoyageRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/devis")
public class DevisController {


    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private DevisService devisService;

    /**
     * Calculer le devis et stocker le voyage
     */
    @PostMapping("/calculer")
    public ResponseEntity<Voyage> calculerEtAjouterVoyage(@Valid @RequestBody Voyage voyage) {
        double devis = devisService.calculerDevis(voyage);
        voyage.setCoutBase(devis); // Met à jour le prix calculé
        voyageRepository.save(voyage);
        return ResponseEntity.ok(voyage);
    }

    /**
     * Récupérer tous les voyages
     */
    @GetMapping
    public List<Voyage> getTousLesVoyages() {
        return voyageRepository.findAll();
    }

    /**
     * Récupérer un voyage par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Voyage> getVoyageParId(@PathVariable Long id) {
        Optional<Voyage> voyage = voyageRepository.findById(id);
        return voyage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Supprimer un voyage
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerVoyage(@PathVariable Long id) {
        if (!voyageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        voyageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

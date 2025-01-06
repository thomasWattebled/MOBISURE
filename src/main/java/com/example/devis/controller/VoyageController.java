package com.example.devis.controller;


import com.example.devis.model.Voyage;
import com.example.devis.service.DevisService;
import com.example.devis.repository.VoyageRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voyages")
public class VoyageController {

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private DevisService devisService;

    @PostMapping
    public ResponseEntity<String> ajouterVoyage(@Valid @RequestBody Voyage voyage) {
        double devis = devisService.calculerDevis(voyage);
        voyageRepository.save(voyage);
        return ResponseEntity.ok("Devis calculé : " + devis);
    }

    @GetMapping
    public List<Voyage> getTousLesVoyages() {
        return voyageRepository.findAll();
    }
}

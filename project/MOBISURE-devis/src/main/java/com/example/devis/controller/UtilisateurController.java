package com.example.devis.controller;

import com.example.devis.model.Utilisateur;
import com.example.devis.repository.UtilisateurRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping
    public ResponseEntity<Utilisateur> ajouterUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        Utilisateur nouvelUtilisateur = utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok(nouvelUtilisateur);
    }

    @GetMapping
    public List<Utilisateur> getTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }
}

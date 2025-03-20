package com.example.devis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devis.entity.Assurance;
import com.example.devis.entity.vehicule.Moto;
import com.example.devis.entity.vehicule.Velo;
import com.example.devis.entity.vehicule.Voiture;
import com.example.devis.request.MotoRequest;
import com.example.devis.request.VeloRequest;
import com.example.devis.request.VoitureRequest;
import com.example.devis.service.AssuranceServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/contrat")
public class AssuranceController {

	@Autowired
	private AssuranceServiceImpl service;
	
	@PostMapping("/devisVoiture")
    public ResponseEntity<Double> calculerDevisVoiture(@Valid @RequestBody VoitureRequest request){
		
		Voiture voiture = new Voiture(request.getClientId(),request.getMarque(),request.getMotorisation(),request.getFabrication(),request.getUtilisation(),request.getDuree(),request.getModele(),request.getPlaque());
		double prix = service.createVoitureDevis(voiture);  
		return ResponseEntity.ok(prix);
	}
	
	@PostMapping("/devisMoto")
    public ResponseEntity<Double> calculerDevisMoto(@Valid @RequestBody MotoRequest request){
		
		Moto moto = new Moto(request.getClientId(),request.getMarque(),request.getMotorisation(),request.getFabrication(),request.getUtilisation(),request.getDuree(),request.getModele(),request.getPlaque());
		double prix = service.createMotoDevis(moto);  
		return ResponseEntity.ok(prix);
	}
	
	@PostMapping("/devisVelo")
    public ResponseEntity<Double> calculerDevisVelo(@Valid @RequestBody VeloRequest request){
		
		Velo velo = new Velo(request.getClientId(),request.getMotorisation());
		double prix = service.createVeloDevis(velo);  
		return ResponseEntity.ok(prix);
	}
	
	@PostMapping("/createVoiture")
	public ResponseEntity<Voiture> createVoiture(@Valid @RequestBody VoitureRequest request){
		Voiture voiture = new Voiture(request.getClientId(),request.getMarque(),request.getMotorisation(),request.getFabrication(),request.getUtilisation(),request.getDuree(),request.getModele(),request.getPlaque());
		service.createContratVoiture(voiture);
		return ResponseEntity.ok(voiture);
	}
	
	@PostMapping("/createMoto")
	public ResponseEntity<Moto> createMoto(@Valid @RequestBody MotoRequest request){
		Moto moto = new Moto(request.getClientId(),request.getMarque(),request.getMotorisation(),request.getFabrication(),request.getUtilisation(),request.getDuree(),request.getModele(),request.getPlaque());
		service.createContratMoto(moto);
		return ResponseEntity.ok(moto);
	}
	
	@PostMapping("/createVelo")
	public ResponseEntity<Velo> createVelo(@Valid @RequestBody VeloRequest request){
		Velo velo = new Velo(request.getClientId(),request.getMotorisation());
		service.createContratVelo(velo);
		return ResponseEntity.ok(velo);
	}
	
	@GetMapping("/all/assurance")
	public ResponseEntity<List<Assurance>> getAllAssurance(){
		List<Assurance> assurances = service.getAllAssurance();
		return ResponseEntity.ok(assurances);
	}
	
	@GetMapping("/my/assurance/{clientId}")
	public ResponseEntity<List<Assurance>> getMyAssurance(@PathVariable Long clientId){
		List<Assurance> assurances = service.getAssuranceByClientId(clientId);
		return ResponseEntity.ok(assurances);
	}
	
}

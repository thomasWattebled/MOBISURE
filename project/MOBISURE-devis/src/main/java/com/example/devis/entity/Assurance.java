package com.example.devis.entity;

import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.devis.Enum.TypeAssurance;

@Document
public class Assurance {

	@Id
	private String id;
	private Long clientId;
	private TypeAssurance type;
	
	@Indexed(unique = true)
    private String numDossier;
	
	public Assurance() {}
	
	public Assurance(Long clientId,TypeAssurance type) {
		this.clientId = clientId;
		this.type = type;
		this.numDossier = generateNumDossier();
	}
	
	private String generateNumDossier() {
        return "A-" + (100000 + new Random().nextInt(900000));
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TypeAssurance getType() {
		return type;
	}

	public void setType(TypeAssurance type) {
		this.type = type;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}
	
}

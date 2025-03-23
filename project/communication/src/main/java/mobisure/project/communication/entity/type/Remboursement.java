package mobisure.project.communication.entity.type;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;

@Document
public class Remboursement extends Assistance {

	private float montant;
	private String motif;
	
	
	public Remboursement(Long idClient, Status status, Date date, String message, TypeAssistance type, String nom,
			String prenom, String mail, String mdp, String telephone, float montant, String motif) {
		
		super(idClient, status, date, message, type, nom, prenom, mail, mdp, telephone);
		this.montant = montant;
		this.motif = motif;
	}


	public float getMontant() {
		return montant;
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}


	public String getMotif() {
		return motif;
	}


	public void setMotif(String motif) {
		this.motif = motif;
	}
	
}

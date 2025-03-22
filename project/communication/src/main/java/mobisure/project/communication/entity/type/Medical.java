package mobisure.project.communication.entity.type;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;

@Document
public class Medical extends Assistance{

	private String motif;

	public Medical(Long idClient, Status status, Date date, String message, TypeAssistance type, String nom,
			String prenom, String mail, String mdp, String telephone, String motif) {
		
		super(idClient, status, date, message, type, nom, prenom, mail, mdp, telephone);
		this.motif = motif;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	
	
}

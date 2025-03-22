package mobisure.project.communication.entity.type;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;

@Document
public class Depannage extends Assistance{

	private String ville;
	private String rue;
	
	public Depannage(Long idClient, Status status, Date date, String message, TypeAssistance type, String nom,
			String prenom, String mail, String mdp, String telephone, String ville, String rue) {
		
		super(idClient, status, date, message, type, nom, prenom, mail, mdp, telephone);
		this.ville = ville;
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}	
	
}

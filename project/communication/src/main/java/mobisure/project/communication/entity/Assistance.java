package mobisure.project.communication.entity;

import java.util.Date;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Assistance {
	
	@Id
	private String id;

	@Indexed(unique = true)
    private String numDossier;
	
	private Long idClient;
	private Status status;
	private Date date;
	private String message;
	
	private TypeAssistance type;
	private String nom;
	private String prenom;
	private String mail;
	private String mdp;
	private String telephone;
	
	private boolean gerer;
	
	
	public Assistance(Long id_client, Status status, Date date, String message, TypeAssistance type, String nom,
			String prenom, String mail, String mdp, String telephone) {
		super();
		this.idClient = id_client;
		this.status = status;
		this.date = date;
		this.message = message;
		this.type = type;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.mdp = mdp;
		this.telephone = telephone;
		this.numDossier = generateNumDossier();
		this.gerer = false;
	}

	public Assistance() {
		this.numDossier = generateNumDossier();
		this.gerer = false;
	}
	
	private String generateNumDossier() {
        return "D-" + (100000 + new Random().nextInt(900000));
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TypeAssistance getType() {
		return type;
	}

	public void setType(TypeAssistance type) {
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public boolean isGerer() {
		return gerer;
	}

	public void setGerer(boolean gerer) {
		this.gerer = gerer;
	}
	

}

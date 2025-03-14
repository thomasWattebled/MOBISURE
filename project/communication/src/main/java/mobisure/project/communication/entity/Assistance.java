package mobisure.project.communication.entity;

import java.util.Date;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Assistance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
    private String numDossier;
	
	private Long idClient;
	private Status status;
	private Date date;
	
	@Column(length = 1000)
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

    public String getNum_dossier() {
        return numDossier;
    }
    
    public void setNum_dossier(String num_dossier) {
        this.numDossier = num_dossier;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_client() {
		return idClient;
	}

	public void setId_client(Long id_client) {
		this.idClient = id_client;
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

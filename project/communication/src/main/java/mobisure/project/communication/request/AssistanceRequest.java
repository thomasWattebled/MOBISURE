package mobisure.project.communication.request;

public class AssistanceRequest {

	private Long id_client;
    private String status;
    private String date;
    private String message;
    private String type;
    private String nom;
	private String prenom;
	private String mail;
	private String mdp;
	private String telephone;
	
	//Pour depannage et accident : 
	private String ville;
	private String rue;
	
	//Pour accident :
	private int nbBlesse;
	
	//Pour remboursement :
	private float montant;
	private String motif; // et pour medical
    
	public AssistanceRequest() {}

	public AssistanceRequest(Long id_client, String status, String date, String message, String type, String nom,
			String prenom, String mail, String mdp, String telephone,String ville, String rue,int nbBlesse, float montant, String motif) {
		super();
		this.id_client = id_client;
		this.status = status;
		this.date = date;
		this.message = message;
		this.type = type;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.mdp = mdp;
		this.telephone = telephone;
		this.ville = ville;
		this.rue = rue;
		this.nbBlesse = nbBlesse;
		this.montant = montant;
		this.motif = motif;
	}

	public Long getId_client() {
		return id_client;
	}

	public void setId_client(Long id_client) {
		this.id_client = id_client;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public int getNbBlesse() {
		return nbBlesse;
	}

	public void setNbBlesse(int nbBlesse) {
		this.nbBlesse = nbBlesse;
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

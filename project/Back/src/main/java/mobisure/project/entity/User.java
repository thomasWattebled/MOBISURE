package mobisure.project.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Client")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "email")
	private String mail;
	
	@Column(name = "mot_de_passe")
	private String mdp;
	
	@Column(name = "civilite")
	private String sexe;
	
	@Column(name = "date_naissance")
	private Date dateNaissance;
	
	@Column(name = "adresse")
	private String adresse;
	
	@Column(name = "numero_client")
	private String numero_client;
	
	@Column(name = "phone_number")
	private String telephone;
	
	@Column(name = "date_creation")
	private Date date_creation;
	
	private Set<RoleName> roles = new HashSet<>();

	
	public User(){}

	public User(String nom, String prenom, String mail, String mdp, String sexe, Date dateNaissance, String adresse,
			String numero_client, String telephone, Date date_creation, Set<RoleName> roles) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.mdp = mdp;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.numero_client = numero_client;
		this.telephone = telephone;
		this.date_creation = date_creation;
		this.roles = roles;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<RoleName> getRoles() {
		return roles;
	}
	
	public void addRole(RoleName role) {
		this.roles.add(role);
	}

	public void setRoles(Set<RoleName> roles) {
		this.roles = roles;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNumero_client() {
		return numero_client;
	}

	public void setNumero_client(String numero_client) {
		this.numero_client = numero_client;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adresse, dateNaissance, date_creation, id, mail, mdp, nom, numero_client, prenom, roles,
				sexe, telephone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(dateNaissance, other.dateNaissance)
				&& Objects.equals(date_creation, other.date_creation) && Objects.equals(id, other.id)
				&& Objects.equals(mail, other.mail) && Objects.equals(mdp, other.mdp) && Objects.equals(nom, other.nom)
				&& Objects.equals(numero_client, other.numero_client) && Objects.equals(prenom, other.prenom)
				&& Objects.equals(roles, other.roles) && Objects.equals(sexe, other.sexe)
				&& Objects.equals(telephone, other.telephone);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", mdp=" + mdp + ", sexe="
				+ sexe + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", numero_client="
				+ numero_client + ", telephone=" + telephone + ", date_creation=" + date_creation + ", roles=" + roles
				+ "]";
	}

	
	
	
	
}
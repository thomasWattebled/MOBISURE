package mobisure.project.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import mobisure.project.entity.RoleName;

/*
 * Data transfer class (DTO) representing a user.
 * This class is used to transfer data between application layers
 * while respecting the principle of separation of responsibilities.
 */

public class UserDto {

	private Long id;
	private String nom;
	private String prenom;
	private String mail;
	private String mdp;
	private String sexe;
	private Date dateNaissance;
	private String adresse;
	private String telephone;
	private Date date_creation;
	private Set<RoleName> roles = new HashSet<>();
	
	/*
	 * Constructor without arguments for creating an instance of another class
	 */
	public UserDto(){}

	/**
	 * 
	 * @param nom : the name of the user.
	 * @param prenom : the user's first name.
	 * @param mail : the user’s email.
	 * @param mdp : the user’s password.
	 * @param sexe : the gender of the user.
	 * @param dateNaissance : the date of birth of the user.
	 * @param adresse : the address of the user.
	 * @param telephone : the user’s phone number.
	 * @param date_creation : the date of creation of the user.
	 */
	public UserDto(String nom, String prenom, String mail, String mdp, String sexe, Date dateNaissance, String adresse,
			String telephone, Date date_creation) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.mdp = mdp;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.telephone = telephone;
		this.date_creation = date_creation;
		Set<RoleName> roles = new HashSet<>();
	}

	/*
	 *Getter and setter for all attributes 
	 */
	
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
		return Objects.hash(adresse, dateNaissance, date_creation, id, mail, mdp, nom, prenom, roles, sexe, telephone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(dateNaissance, other.dateNaissance)
				&& Objects.equals(date_creation, other.date_creation) && Objects.equals(id, other.id)
				&& Objects.equals(mail, other.mail) && Objects.equals(mdp, other.mdp) && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom) && Objects.equals(roles, other.roles)
				&& Objects.equals(sexe, other.sexe) && Objects.equals(telephone, other.telephone);
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", mdp=" + mdp
				+ ", sexe=" + sexe + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", telephone="
				+ telephone + ", date_creation=" + date_creation + ", roles=" + roles + "]";
	}

	
	
	
}

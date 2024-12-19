package mobisure.project.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import mobisure.project.entity.RoleName;

public class UserDto {

	private Long id;
	private String nom;
	private String prenom;
	private String mail;
	private String mdp;
	private Set<RoleName> roles = new HashSet<>();
	
	public UserDto(){}

	public UserDto(String nom, String prenom, String mail, String mdp) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.mdp = mdp;
		Set<RoleName> roles = new HashSet<>();
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
	

	@Override
	public int hashCode() {
		return Objects.hash(id, mail, mdp, nom, prenom, roles);
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
		return Objects.equals(id, other.id) && Objects.equals(mail, other.mail) && Objects.equals(mdp, other.mdp)
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom)
				&& Objects.equals(roles, other.roles);
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	
	
}

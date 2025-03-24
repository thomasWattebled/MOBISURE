package mobisure.project.communication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long expediteurId;
	
	private Long receveurId;
	
	private String contenu;
	
	public Message() {}

	public Message(Long expediteurId, Long receveurId, String contenue) {
		super();
		this.receveurId = receveurId;
		this.expediteurId = expediteurId;
		this.contenu = contenue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReceveurId() {
		return receveurId;
	}

	public void setReceveurId(Long receveurId) {
		this.receveurId = receveurId;
	}

	public Long getExpediteurId() {
		return expediteurId;
	}

	public void setExpediteurId(Long expediteurId) {
		this.expediteurId = expediteurId;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenue(String contenu) {
		this.contenu = contenu;
	}
	
	
	
}

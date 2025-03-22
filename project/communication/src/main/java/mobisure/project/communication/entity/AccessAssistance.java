package mobisure.project.communication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AccessAssistance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String idAssistance;
	private Long idUser;
	
	public AccessAssistance() {}

	public AccessAssistance(String idAssistance, Long idUser) {
		super();
		this.idAssistance = idAssistance;
		this.idUser = idUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdAssistance() {
		return idAssistance;
	}

	public void setIdAssistance(String idAssistance) {
		this.idAssistance = idAssistance;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	
	
	
}

package mobisure.project.communication.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Assistance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Long id_client;
	private Status status;
	private Date date;
	private String message;
	private TypeAssistance type;
	
	public Assistance(Long id_client, Status status, Date date, String message, TypeAssistance type) {
		super();
		this.id_client = id_client;
		this.status = status;
		this.date = date;
		this.message = message;
		this.type = type;
	}
	
	public Assistance() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_client() {
		return id_client;
	}

	public void setId_client(Long id_client) {
		this.id_client = id_client;
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
	
	

}

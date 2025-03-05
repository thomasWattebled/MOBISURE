package mobisure.project.communication.request;

public class AssistanceRequest {

	private Long id_client;
    private String status;
    private String date;
    private String message;
    private String type;
    
	public AssistanceRequest(Long id_client, String status, String date, String message, String type) {
		super();
		this.id_client = id_client;
		this.status = status;
		this.date = date;
		this.message = message;
		this.type = type;
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
    
	
    
	
}

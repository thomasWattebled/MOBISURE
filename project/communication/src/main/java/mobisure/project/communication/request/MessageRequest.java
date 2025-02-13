package mobisure.project.communication.request;

public class MessageRequest {

	private Long expediteurId;
    private Long receveurId;
    private String contenu;
    
    public MessageRequest() {}
    
	public Long getExpediteurId() {
		return expediteurId;
	}
	public void setExpediteurId(Long expediteurId) {
		this.expediteurId = expediteurId;
	}
	public Long getReceveurId() {
		return receveurId;
	}
	public void setReceveurId(Long receveurId) {
		this.receveurId = receveurId;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
    
    
}

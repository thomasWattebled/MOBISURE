package mobisure.project.communication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Document {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String numDossier;
	
	private String name;
    
    private String type; // Type MIME (ex: "application/pdf", "image/png")

    @Lob
    private byte[] data; // Stocker le fichier en binaire
    
    public Document() {}

	public Document(String num_dossier, String name, String type, byte[] data) {
		super();
		this.numDossier = num_dossier;
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNum_dossier() {
		return numDossier;
	}

	public void setNum_dossier(String num_dossier) {
		this.numDossier = num_dossier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
}

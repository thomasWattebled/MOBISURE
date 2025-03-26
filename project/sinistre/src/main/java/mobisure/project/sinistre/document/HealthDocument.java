package mobisure.project.sinistre.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "health") // Specify the MongoDB collection name
public class HealthDocument {

	@Id
	private String id;

	private Long sinistreId;
	private Long contractId;
	private String category;
	private Date date;
	private String nature;
	private String description;
	private String hospital;

	public HealthDocument() {
	}

	public HealthDocument(Long sinistreId, Long contractId, String category, Date date, String nature,
			String description, String hospital) {
		this.contractId = contractId;
		this.category = category;
		this.date = date;
		this.nature = nature;
		this.description = description;
		this.hospital = hospital;
		this.sinistreId = sinistreId;
	}

	public Long getSinistreId() {
		return sinistreId;
	}

	public void setSinistreId(Long sinistreId) {
		this.sinistreId = sinistreId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

}

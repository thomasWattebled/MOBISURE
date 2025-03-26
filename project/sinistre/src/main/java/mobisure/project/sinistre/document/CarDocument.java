package mobisure.project.sinistre.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "car") // Specify the MongoDB collection name
public class CarDocument {

	@Id
	private String id;

	private Long sinistreId;
	private Long contractId;
	private String category;
	private Date date;
	private String immatriculation;
	private String brand;
	private String modele;
	private String description;
	private String responsable;

	public CarDocument() {
	}

	public CarDocument(Long sinistreId, Long contractId, String category, Date date, String immatriculation,
			String brand, String modele, String description, String responsable) {
		this.contractId = contractId;
		this.category = category;
		this.date = date;
		this.immatriculation = immatriculation;
		this.brand = brand;
		this.modele = modele;
		this.description = description;
		this.responsable = responsable;
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

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

}

package mobisure.project.sinistre.dto;

import java.util.Date;

public class SinistreCarDto {
	private Long id;
	private Long contractId;
	private Long userId;
	private String category;
	private Date date;
	private String immatriculation;
	private String brand;
	private String modele;
	private String description;
	private String responsable;

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public SinistreCarDto(Long contractId, Long userId, String category, Date date, String immatriculation,
			String brand, String modele, String description, String responsable) {
		this.contractId = contractId;
		this.userId = userId;
		this.category = category;
		this.date = date;
		this.brand = brand;
		this.modele = modele;
		this.description = description;
		this.responsable = responsable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

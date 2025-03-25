package mobisure.project.dto;

import java.util.Date;

public class SinistreHealthDto {
	
	private Long id;
	private Long contractId;
	private Long userId;
	private String category;
	private Date date;
	private String nature;
	private String description;
	private String hospital;
	
	public SinistreHealthDto (Long contractId, Long userId, String category, Date date, String nature, String description, String hospital) {
		this.contractId = contractId;
		this.userId = userId;
		this.category = category;
		this.date = date;
		this.nature = nature;
		this.description = description;
		this.hospital = hospital;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	
	
}

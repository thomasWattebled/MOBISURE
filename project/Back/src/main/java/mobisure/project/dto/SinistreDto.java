package mobisure.project.dto;

import java.util.Date;
import java.util.Objects;

public class SinistreDto {
	
	private Long id;
	private Long contractId;
	private Long userId;
	private String category;
	private Date date;
	
	public SinistreDto(){}
	
	public SinistreDto (Long contractId, Long userId, String category, Date date) {
		super();
		this.contractId = contractId;
		this.userId = userId;
		this.category = category;
		this.date = date;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id, contractId, userId, category, date);
	}

	@Override
	public String toString() {
		return "SinistreDto [id=" + id + ", contractId=" + contractId + ", userId=" + userId + ", category=" + category
				+ ", date=" + date + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinistreDto other = (SinistreDto) obj;
		return contractId == other.getContractId() && userId == other.getUserId() && category.equals(other.getCategory()) && date.equals(other.getDate());
	}

}

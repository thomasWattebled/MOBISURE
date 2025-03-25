package mobisure.project.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import mobisure.project.dto.SinistreDto;

@Entity
@Table(name = "Sinistre")
public class Sinistre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "contractId")
	private Long contractId;
	
	@Column(name = "userId")
	private Long userId;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "date")
	private Date date;
	
	public Sinistre () {}
	
	public Sinistre (Long contractId, Long userId, String category, Date date) {
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

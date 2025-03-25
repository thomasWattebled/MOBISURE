package mobisure.project.service;

import java.util.List;

import mobisure.project.document.HealthDocument;



public interface HealthService {
	public HealthDocument saveHealth (HealthDocument healthDocument);
	public List<HealthDocument> getBySinistreId (Long sinistreId);
}

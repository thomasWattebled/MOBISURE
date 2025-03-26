package mobisure.project.sinistre.service;

import java.util.List;


import mobisure.project.sinistre.document.HealthDocument;

public interface HealthService {
	public HealthDocument saveHealth(HealthDocument healthDocument);

	public List<HealthDocument> getBySinistreId(Long sinistreId);
}

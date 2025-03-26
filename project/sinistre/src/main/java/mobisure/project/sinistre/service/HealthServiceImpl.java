package mobisure.project.sinistre.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.sinistre.document.HealthDocument;
import mobisure.project.sinistre.repository.HealthRepository;

@Service
public class HealthServiceImpl implements HealthService {

	@Autowired
	HealthRepository healthRepo;

	public HealthDocument saveHealth(HealthDocument healthDocument) {
		return this.healthRepo.save(healthDocument);
	}

	public List<HealthDocument> getBySinistreId(Long sinistreId) {
		return this.healthRepo.findBySinistreId(sinistreId);
	}
}

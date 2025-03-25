package mobisure.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.document.CarDocument;
import mobisure.project.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	CarRepository carRepo;
	
	public CarDocument saveCar (CarDocument carDocument) {
		return this.carRepo.save(carDocument);
	}
	
	public List<CarDocument> getBySinistreId (Long sinistreId) {
		return this.carRepo.findBySinistreId(sinistreId);
	}
	
}

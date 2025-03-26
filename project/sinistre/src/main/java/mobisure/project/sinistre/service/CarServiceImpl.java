package mobisure.project.sinistre.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.sinistre.document.CarDocument;
import mobisure.project.sinistre.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarRepository carRepo;

	public CarDocument saveCar(CarDocument carDocument) {
		return this.carRepo.save(carDocument);
	}

	public List<CarDocument> getBySinistreId(Long sinistreId) {
		return this.carRepo.findBySinistreId(sinistreId);
	}

}

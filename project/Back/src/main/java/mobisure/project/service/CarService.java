package mobisure.project.service;

import java.util.List;

import mobisure.project.document.CarDocument;

public interface CarService {
	public CarDocument saveCar (CarDocument carDocument);
	public List<CarDocument> getBySinistreId (Long sinistreId);
}

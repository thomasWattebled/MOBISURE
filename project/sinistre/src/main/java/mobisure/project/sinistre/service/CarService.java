package mobisure.project.sinistre.service;

import java.util.List;


import mobisure.project.sinistre.document.CarDocument;

public interface CarService {
	public CarDocument saveCar(CarDocument carDocument);

	public List<CarDocument> getBySinistreId(Long sinistreId);
}

package mobisure.project.sinistre.service;

import mobisure.project.sinistre.document.CarDocument;
import mobisure.project.sinistre.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepo;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void testSaveCar() {
        Long sinistreId = 123L;
        Long contractId = 456L;
        String category = "Accident";
        Date date = new Date();
        String immatriculation = "1234ABC";
        String brand = "Toyota";
        String modele = "Camry";
        String description = "Collision with another car";
        String responsable = "Driver";

        CarDocument carDocument = new CarDocument(sinistreId, contractId, category, date, immatriculation, brand, modele, description, responsable);
        
        when(carRepo.save(carDocument)).thenReturn(carDocument);

        CarDocument savedCar = carService.saveCar(carDocument);
        assertNotNull(savedCar);
        assertEquals(immatriculation, savedCar.getImmatriculation());
        assertEquals(brand, savedCar.getBrand());
    }

    @Test
    void testGetBySinistreId() {
        Long sinistreId = 123L;
        CarDocument car1 = new CarDocument(sinistreId, 456L, "Accident", new Date(), "1234ABC", "Toyota", "Camry", "Collision with another car", "Driver");
        CarDocument car2 = new CarDocument(sinistreId, 789L, "Accident", new Date(), "5678XYZ", "Honda", "Civic", "Rear-ended", "Driver");

        List<CarDocument> cars = Arrays.asList(car1, car2);

        when(carRepo.findBySinistreId(sinistreId)).thenReturn(cars);

        List<CarDocument> result = carService.getBySinistreId(sinistreId);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Toyota", result.get(0).getBrand());
    }
}

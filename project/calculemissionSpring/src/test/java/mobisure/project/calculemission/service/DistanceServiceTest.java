package mobisure.project.calculemission.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DistanceServiceTest {

    private DistanceService distanceService;

    @BeforeEach
    void setUp() {
        distanceService = new DistanceService();
    }

    @Test
    void testToRad() {
        assertEquals(Math.PI, distanceService.toRad(180), 1e-6);
        assertEquals(0, distanceService.toRad(0), 1e-6);
        assertEquals(Math.PI / 2, distanceService.toRad(90), 1e-6);
    }

    @Test
    void testCrowDistance() {

    	double distance = distanceService.crowDistance("48.8566,2.3522", "45.7640,4.8357");
        assertEquals(392000, distance, 1000);
        
        double samePointDistance = distanceService.crowDistance("48.8566,2.3522", "48.8566,2.3522");
        assertEquals(0, samePointDistance, 1000);
    }
}

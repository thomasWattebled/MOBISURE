package mobisure.project.calculemission.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mobisure.project.calculemission.service.DistanceService;
import mobisure.project.calculemission.service.ImpactCOD;
import mobisure.project.calculemission.service.OpenRouteService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CalculEmissionController {

	private ImpactCOD impactCOD = new ImpactCOD();
	private OpenRouteService openRouteService = new OpenRouteService(
			"5b3ce3597851110001cf6248f4370619d17745c38e112fff99705c37");
	private DistanceService distanceService = new DistanceService();

	@GetMapping(("/calculemission/getemission"))
	public double getEmission(@RequestParam String gpsStart, @RequestParam String gpsEnd, @RequestParam int transport) {
		double emission = -1.0;

		double distance = 0.0;

		if (transport == 1) {
			distance = this.distanceService.crowDistance(gpsStart, gpsEnd);
		} else {
			distance = this.openRouteService.getDistanceRoute(gpsStart, gpsEnd);
		}

		emission = this.impactCOD.getEmission(transport, distance / 1000);

		return emission;
	}
	
	@GetMapping(("/calculemission/getdistance"))
	public double getDistance(@RequestParam String gpsStart, @RequestParam String gpsEnd, @RequestParam int transport) {

		double distance = -1.0;

		if (transport == 1) {
			distance = this.distanceService.crowDistance(gpsStart, gpsEnd);
		} else {
			distance = this.openRouteService.getDistanceRoute(gpsStart, gpsEnd);
		}

		return distance;
	}
}

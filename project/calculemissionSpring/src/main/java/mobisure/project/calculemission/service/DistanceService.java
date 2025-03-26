package mobisure.project.calculemission.service;

public class DistanceService {

	public DistanceService() {

	}

	public double toRad(double value) {
		return value * Math.PI / 180;
	}

	public double crowDistance(String gpsStart, String gpsEnd) {
		double lat1 = Double.parseDouble(gpsStart.split(",")[0]);
		double lat2 = Double.parseDouble(gpsEnd.split(",")[0]);
		double lng1 = Double.parseDouble(gpsStart.split(",")[1]);
		double lng2 = Double.parseDouble(gpsEnd.split(",")[1]);

		double R = 6371; // km
		double dLat = toRad(lat2 - lat1);
		double dLon = toRad(lng2 - lng1);
		lat1 = toRad(lat1);
		lat2 = toRad(lat2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c * 1000;

		System.out.println("Distance à vol d'oiseau : " + d + " m");

		return d;
	}
}

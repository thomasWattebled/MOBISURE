package mobisure.calculemission;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

public class OpenRouteService {
	private String apiKey;
	
	public OpenRouteService() {
		
	}
	
	public OpenRouteService(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public double getDistance (String gpsStart, String gpsEnd) {
		Client client = ClientBuilder.newClient();
		String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=" + apiKey + "&start=" + gpsStart + "&end=" + gpsEnd + "&units=km";
		WebTarget target = client.target(url);
		double distance = -1.0;
		
		try (Response response = target.request().get()) {
            if (response.getStatus() != 200) {
                System.err.println("Erreur lors de la requête : " + response.getStatus() + " " + response.readEntity(String.class));
                return distance;
            }

            String responseBody = response.readEntity(String.class);
            JSONObject json = new JSONObject(responseBody);

            distance = json.getJSONArray("features").getJSONObject(0)
                    .getJSONObject("properties").getJSONObject("summary").getDouble("distance");

            System.out.println("Distance : " + distance + " m");

        } catch (Exception e) {
            System.err.println("Une erreur s'est produite : " + e.getMessage());
        } finally {
            client.close();
        }
		
		return distance;
		
	}
	
}

package mobisure.project.calculemission.service;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

public class ImpactCOD {

    public ImpactCOD() {

    }

    public double getEmission(int transport, double kilometers) {
        Client client = ClientBuilder.newClient();
        String url = "https://impactco2.fr/api/v1/transport?km=" + (int) kilometers + "&displayAll=0&transports="
                + transport + "&ignoreRadiativeForcing=0&occupencyRate=1&includeConstruction=0&language=fr";
        WebTarget target = client.target(url);
        double emission = -1.0;

        try (Response response = target.request().get()) {
            if (response.getStatus() != 200) {
                System.err.println("Erreur lors de la requête : " + response.getStatus() + " "
                        + response.readEntity(String.class));
                return emission;
            }

            String responseBody = response.readEntity(String.class);
            JSONObject json = new JSONObject(responseBody);

            System.out.println(json.getJSONArray("data"));

            emission = json.getJSONArray("data").getJSONObject(0)
                    .getDouble("value");

            System.out.println("Emission : " + emission + " kg");

        } catch (Exception e) {
            System.err.println("Une erreur s'est produite : " + e.getMessage());
        } finally {
            client.close();
        }

        return emission;
    }
}

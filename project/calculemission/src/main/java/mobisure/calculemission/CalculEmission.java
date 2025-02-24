package mobisure.calculemission;

import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.GET;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("calculemission")
public class CalculEmission {
	
	private ImpactCOD impactCOD = new ImpactCOD();
	private OpenRouteService openRouteService = new OpenRouteService("5b3ce3597851110001cf6248f4370619d17745c38e112fff99705c37");
	private DistanceService distanceService = new DistanceService();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/getemission")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmission(@QueryParam("gpsStart") String gpsStart,
    		@QueryParam("gpsEnd") String gpsEnd,
    		@QueryParam("transport") int transport){
    	double emission = -1.0;
    	
    	double distance = 0.0;
    	
    	if (transport == 1) {
    		distance = this.distanceService.crowDistance(gpsStart, gpsEnd);
    	} else {
    		distance = this.openRouteService.getDistanceRoute(gpsStart, gpsEnd);
    	}
    	
    	emission = this.impactCOD.getEmission(transport, distance/1000);
    	
    	return Response.ok(emission)
    			.header("Access-Control-Allow-Origin", "*")
    			.build();
    }
}

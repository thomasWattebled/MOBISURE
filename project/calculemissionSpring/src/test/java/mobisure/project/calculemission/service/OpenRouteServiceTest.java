package mobisure.project.calculemission.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class OpenRouteServiceTest {

    private OpenRouteService openRouteService;
    private Client client;
    private WebTarget webTarget;
    private Invocation.Builder builder;
    private Response response;
    private static MockedStatic<ClientBuilder> mockedClientBuilder;

    @BeforeEach
    void setUp() {
        client = mock(Client.class);
        webTarget = mock(WebTarget.class);
        builder = mock(Invocation.Builder.class);
        response = mock(Response.class);

        // 🔥 Mocke ClientBuilder.newClient() seulement s'il n'est pas déjà mocké
        if (mockedClientBuilder == null) {
            mockedClientBuilder = Mockito.mockStatic(ClientBuilder.class);
            mockedClientBuilder.when(ClientBuilder::newClient).thenReturn(client);
        }

        openRouteService = new OpenRouteService("fake-api-key");
    }

    @AfterEach
    void tearDown() {
        if (mockedClientBuilder != null) {
            mockedClientBuilder.close();
            mockedClientBuilder = null;
        }
    }

    @Test
    void testGetDistanceRouteSuccess() {
        String gpsStart = "2.3522,48.8566"; // Paris
        String gpsEnd = "3.0586,50.6292"; // Lille
        String mockResponse = "{\"features\":[{\"properties\":{\"summary\":{\"distance\": 225.4}}}]}";

        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request()).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(String.class)).thenReturn(mockResponse);

        double distance = openRouteService.getDistanceRoute(gpsStart, gpsEnd);

        assertEquals(225.4, distance, 1e-6);
    }

    @Test
    void testGetDistanceRouteFailure() {
        String gpsStart = "2.3522,48.8566";
        String gpsEnd = "3.0586,50.6292";

        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request()).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);
        when(response.readEntity(String.class)).thenReturn("Internal Server Error");

        double distance = openRouteService.getDistanceRoute(gpsStart, gpsEnd);

        assertEquals(-1.0, distance);
    }
}


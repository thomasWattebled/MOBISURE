package mobisure.project.calculemission.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class ImpactCODTest {

    private ImpactCOD impactCOD;
    private Client client;
    private WebTarget webTarget;
    private Invocation.Builder builder;
    private Response response;

    @BeforeEach
    void setUp() {
        client = mock(Client.class);
        webTarget = mock(WebTarget.class);
        builder = mock(Invocation.Builder.class);
        response = mock(Response.class);

        // Mock statique pour intercepter ClientBuilder.newClient()
        try (MockedStatic<ClientBuilder> mockedClientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            mockedClientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            impactCOD = new ImpactCOD(); // Appelle le vrai constructeur
        }
    }

    @Test
    void testGetEmissionSuccess() {
        int transport = 1;
        double kilometers = 100;
        String mockResponse = "{\"data\":[{\"value\": 25.82}]}";

        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request()).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(String.class)).thenReturn(mockResponse);

        double emission = impactCOD.getEmission(transport, kilometers);

        assertEquals(25.82, emission, 1e-6);
    }

    @Test
    void testGetEmissionFailure() {
        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request()).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);
        when(response.readEntity(String.class)).thenReturn("Internal Server Error");

        double emission = impactCOD.getEmission(1, 100);
    }
}


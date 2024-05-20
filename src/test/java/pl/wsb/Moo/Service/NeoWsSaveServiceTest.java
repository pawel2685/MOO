package pl.wsb.Moo.Service;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import pl.wsb.Moo.Repository.OrbitClassRepository;

import static org.mockito.Mockito.*;

class NeoWsSaveServiceTest {

    private NeoWsSaveService neoWsSaveService;
    private RestTemplate restTemplate;
    private OrbitClassRepository orbitClassRepository;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Inicjalizacja mocków i klasy NeoWsSaveService przed każdym testem
        restTemplate = mock(RestTemplate.class);
        orbitClassRepository = mock(OrbitClassRepository.class);
        objectMapper = new ObjectMapper();
        neoWsSaveService = new NeoWsSaveService(restTemplate, orbitClassRepository);
    }

    @Test
    void saveNearEarthObjects() {
        // Przygotowanie odpowiedzi JSON jako string
        String jsonResponse = "{ \"near_earth_objects\": [] }";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        // Wywołanie metody saveNearEarthObjects()
        neoWsSaveService.saveNearEarthObjects();

        // Weryfikacja, czy metoda getForObject() została wywołana
        verify(restTemplate).getForObject(anyString(), eq(String.class));

        // Weryfikacja, czy metoda save() nie została wywołana na repozytorium (ponieważ odpowiedź jest pusta)
        verifyNoMoreInteractions(orbitClassRepository);
    }
}

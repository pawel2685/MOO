package pl.wsb.Moo.Controller;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wsb.Moo.Service.NeoWsSaveService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SaveControllerTest {

    private SaveController saveController;
    private NeoWsSaveService neoWsSaveService;

    @BeforeEach
    void setUp() {
        // Inicjalizacja mocków i klasy SaveController przed każdym testem
        neoWsSaveService = mock(NeoWsSaveService.class);
        saveController = new SaveController(neoWsSaveService);
    }

    @Test
    void saveData() {
        // Wywołanie metody saveData() w SaveController
        String viewName = saveController.saveData();

        // Sprawdzenie, czy metoda zwraca odpowiednią nazwę widoku (przekierowanie)
        assertEquals("redirect:/saved", viewName);

        // Weryfikacja, czy metoda saveNearEarthObjects() została wywołana
        verify(neoWsSaveService).saveNearEarthObjects();
    }
}

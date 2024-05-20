package pl.wsb.Moo.Controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import pl.wsb.Moo.Model.OrbitClassEntity;
import pl.wsb.Moo.Service.NeoWsService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    private HomeController homeController;
    private NeoWsService neoWsService;
    private Model model;

    @BeforeEach
    void setUp() {
        // Inicjalizacja mocków i klasy HomeController przed każdym testem
        neoWsService = mock(NeoWsService.class);
        model = mock(Model.class);
        homeController = new HomeController(neoWsService);
    }

    @Test
    void home() {
        // Przygotowanie danych testowych
        OrbitClassEntity orbitClass = new OrbitClassEntity();
        List<OrbitClassEntity> orbitClassList = new ArrayList<>();

        // Wywołanie metody home() w HomeController
        String viewName = homeController.home(model);

        // Sprawdzenie, czy nazwa widoku jest poprawna
        assertEquals("index", viewName);

    }

    @Test
    void saved() {
        // Wywołanie metody saved() w HomeController i sprawdzenie, czy zwraca odpowiednią nazwę widoku
        String viewName = homeController.saved();
        assertEquals("saved", viewName);
    }
}

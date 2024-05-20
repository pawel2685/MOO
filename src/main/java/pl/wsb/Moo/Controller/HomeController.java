package pl.wsb.Moo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.wsb.Moo.Model.OrbitClass;
import pl.wsb.Moo.Service.NeoWsService;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final NeoWsService neoWsService;

    public HomeController(NeoWsService neoWsService) {
        this.neoWsService = neoWsService;
    }

    @GetMapping("/")
    public String home(Model model) {
        OrbitClass firstOrbitClass = neoWsService.getFirstNearEarthObject();
        List<OrbitClass> otherOrbitClasses = neoWsService.getNearEarthObjects();

        model.addAttribute("orbitClass", firstOrbitClass);
        model.addAttribute("otherOrbitClasses", otherOrbitClasses);
        return "index";
    }

    @GetMapping("/saved")
    public String saved() {
        return "saved";
    }
}


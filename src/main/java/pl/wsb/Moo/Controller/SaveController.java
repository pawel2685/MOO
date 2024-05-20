package pl.wsb.Moo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wsb.Moo.Service.NeoWsSaveService;

@Controller
public class SaveController {

    private final NeoWsSaveService neoWsSaveService;

    @Autowired
    public SaveController(NeoWsSaveService neoWsSaveService) {
        this.neoWsSaveService = neoWsSaveService;
    }

    @PostMapping("/save")
    public String saveData() {
        neoWsSaveService.saveNearEarthObjects();
        return "redirect:/saved";
    }
}


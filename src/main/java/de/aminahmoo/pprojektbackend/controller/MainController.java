package de.aminahmoo.pprojektbackend.controller;

import de.aminahmoo.pprojektbackend.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    //standard route
    @GetMapping("/")
    public String start() {
        return Application.getInstance().getDatabase().getMitarbeiterHandler().getResultSet();
    }

}

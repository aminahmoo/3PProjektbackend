package de.aminahmoo.pprojektbackend.controller;

import de.aminahmoo.pprojektbackend.Application;
import de.aminahmoo.pprojektbackend.database.Mitarbeiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    //standard route
    @GetMapping("/")
    public List<Mitarbeiter> start() {
        return Mitarbeiter.getAllMitarbeiter();
    }

}

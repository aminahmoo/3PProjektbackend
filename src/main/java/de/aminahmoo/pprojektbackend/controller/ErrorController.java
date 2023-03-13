package de.aminahmoo.pprojektbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    //route für fehler
    @GetMapping("/error")
    public String error() {
        return "Sorry, die angegebe Route ist nicht vorhanden!";
    }

}

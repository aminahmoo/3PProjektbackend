package de.aminahmoo.pprojektbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //standart route
    @GetMapping("/")
    public String start() {
        return "Das ist der Start der Web Application.";
    }

}

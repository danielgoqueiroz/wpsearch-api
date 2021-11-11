package com.danielqueiroz.wpsearch.wpsearch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusService {

    @GetMapping("/")
    public String teste() {
        return "Oltasasdasddsa á";
    }
}

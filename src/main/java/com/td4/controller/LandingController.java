package com.td4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {
    @GetMapping("/ping")
    public String pingPong(){
        return "pong";
    }
}

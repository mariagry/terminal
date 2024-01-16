package com.example.terminal.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String registration() {
        return "registration";
    }

    @GetMapping("/console")
    public String console() {
        return "index";
    }
    @GetMapping("/sign-in")
    public String login() {
        return "login";
    }
}

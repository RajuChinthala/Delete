package com.igate.airline.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SringbootJspController {

    @GetMapping("/welcome")
    public String hello(Model model) {
        model.addAttribute("message", "Hello from Spring Boot!");
        return "hello"; // Return the name of the JSP file
    }
}
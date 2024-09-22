package com.igate.airline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("ars")
public class IndexController {

    @GetMapping(value = "/hello")
    public String index() {
        return "index";
    }

}

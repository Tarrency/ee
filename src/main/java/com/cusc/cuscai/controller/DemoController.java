package com.cusc.cuscai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/")
    public String sayHello() {
        return "hello from cuscai!";
    }
}

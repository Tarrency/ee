package com.cusc.cuscai.controller;

import com.cusc.cuscai.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/")
    public Result sayHello() {
        Result r = Result.success("hello from cuscai!");
        System.out.println(r);
        return Result.success("hello from cuscai!");
    }
}

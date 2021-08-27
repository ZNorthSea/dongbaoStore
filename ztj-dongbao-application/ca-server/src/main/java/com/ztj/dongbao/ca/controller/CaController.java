package com.ztj.dongbao.ca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaController {
    @GetMapping("/hello")
    public String hello(){
        return "hi nginx";
    }
}

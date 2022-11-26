package com.app.neos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice/*")
public class testController {
    @GetMapping("test")
    public String test(){
        return "test";
    }
}

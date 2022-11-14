package com.example.neosproject.controller.fix;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fix/*")
public class FixController {
    @GetMapping("/header")
    public String fix(){
        return "app/fix/header";
    }
    @GetMapping("/loginHeader")
    public String login(){
        return "app/fix/loginHeader";
    }
}

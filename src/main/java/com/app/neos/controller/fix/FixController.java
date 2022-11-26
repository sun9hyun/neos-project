package com.app.neos.controller.fix;

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
    @GetMapping("/login-header")
    public String login(){
        return "app/fix/loginHeader";
    }
    @GetMapping("/charge")
    public String charge(){
        return "app/fix/charge";
    }
    @GetMapping("/charge-input")
    public String chargeInput(){
        return "app/fix/chargeInput";
    }
}

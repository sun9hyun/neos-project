package com.example.neosproject.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/*")
public class MainController {
    @GetMapping("/main")
    public String login(){
        return "app/main/main";
    }
    @GetMapping("/loginMain")
    public String loginMain(){
        return "app/main/loginMain";
    }
}

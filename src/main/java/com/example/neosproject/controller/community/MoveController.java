package com.example.neosproject.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/community/*")
public class MoveController {

    @GetMapping("/frag")
    public String frag(){
        return "app/community/fragmentTest";
    }
}

package com.example.neosproject.controller.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study/*")
public class StudyController {

    @GetMapping("/create")
    public String create(){
        return "app/study/createStudy";
    }

    @GetMapping("/list")
    public String list(){
        return "app/study/listStudy";
    }

    @GetMapping("/detail")
    public String detail(){
        return "app/study/detailStudy";
    }

    @GetMapping("/question")
    public String question(){
        return "app/study/questionStudy";
    }

}

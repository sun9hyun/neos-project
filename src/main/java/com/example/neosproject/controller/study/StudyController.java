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
        return "app/study/detail-study";
    }

    @GetMapping("/question")
    public String question(){
        return "app/study/questionStudy";
    }

    @GetMapping("/feed")
    public String feed(){
        return "app/study/feed-study";
    }

    @GetMapping("/management")
    public String management(){
        return "app/study/studyManagement";
    }

    @GetMapping("/work")
    public String work(){
        return "app/study/workStudy";
    }



    @GetMapping("/test")
    public String test(){
        return "app/study/test";
    }

}

package com.example.neosproject.controller.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage/*")
public class MyPageController {
    @GetMapping("/myPage")
    public String myPage(){
        return "app/myPage/myPage";
    }

    @GetMapping("/myPageAlarm")
    public String myPageAlarm(){
        return "app/myPage/myPageAlarm";
    }

    @GetMapping("/myPageNeos")
    public String myPageNeos(){
        return "app/myPage/myPageNeos";
    }

    @GetMapping("/myPageStore")
    public String myPageStore(){
        return "app/myPage/myPageStore";
    }

    @GetMapping("/myPageStudy")
    public String myPageStudy(){
        return "app/myPage/myPageStudy";
    }

    @GetMapping("/myPageFavorite")
    public String myPageFavorite(){
        return "app/myPage/myPageFavorite";
    }
}

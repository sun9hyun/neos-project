package com.app.neos.controller.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-page/*")
public class MyPageController {

    @GetMapping("/my")
    public String my(){
        return "app/myPage/my";
    }
}

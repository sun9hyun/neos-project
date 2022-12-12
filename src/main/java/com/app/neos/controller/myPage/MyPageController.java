package com.app.neos.controller.myPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/my-page/*")
public class MyPageController {

    @GetMapping("/my")
    public String my(){
        return "app/myPage/my";
    }
}

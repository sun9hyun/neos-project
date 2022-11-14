package com.example.neosproject.controller.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inquiry/*")
public class InquiryController {
    @GetMapping("/list")
    public String listBefore(){
        return "app/inquiry/inquiry";
    }

    @GetMapping("/reply")
    public String listAfter(){
        return "app/inquiry/inquiryReply";
    }


}

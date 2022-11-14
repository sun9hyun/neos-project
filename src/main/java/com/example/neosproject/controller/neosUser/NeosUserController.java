package com.example.neosproject.controller.neosUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/neosUser/*")
public class NeosUserController {

    @GetMapping("/listBefore")
    public String listBefore(){
        return "app/neosUser/neosListBefore";
    }

    @GetMapping("/listAfter")
    public String listAfter(){
        return "app/neosUser/neosListAfter";
    }

    @GetMapping("/info")
    public String info(){
        return "app/neosUser/userInfo";
    }

    @GetMapping("/infoAllNo")
    public String infoAllNo(){
        return "app/neosUser/userInfoAllNo";
    }

}

package com.example.neosproject.controller.neosUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/neosUser/*")
public class NeosUserController {

    @GetMapping("/list-before")
    public String listBefore(){
        return "app/neosUser/neosListBefore";
    }

    @GetMapping("/list-after")
    public String listAfter(){
        return "app/neosUser/neosListAfter";
    }

    @GetMapping("/info-yes")
    public String infoYes(){
        return "app/neosUser/userInfoYes";
    }

    @GetMapping("/info-no-login")
    public String infoNoLogin(){
        return "app/neosUser/userInfoNoLogin";
    }

    @GetMapping("/user-info-not-invite")
    public String userInfoNotInvite(){
        return "app/neosUser/userInfoNotInvite";
    }

    @GetMapping("/my-info")
    public String myInfo(){
        return "app/neosUser/myInfo";
    }

    @GetMapping("/chat")
    public String chat(){
        return "app/fix/chattingBase";
    }

    @GetMapping("/chat-all")
    public String chatAll(){
        return "app/fix/chattingAll";
    }

    @GetMapping("/modal")
    public String modal(){
        return "app/neosUser/modalList";
    }



}

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

    @GetMapping("/infoYes")
    public String infoYes(){
        return "app/neosUser/userInfoYes";
    }

    @GetMapping("/infoNoLogin")
    public String infoNoLogin(){
        return "app/neosUser/userInfoNoLogin";
    }

    @GetMapping("/userInfoNotInvite")
    public String userInfoNotInvite(){
        return "app/neosUser/userInfoNotInvite";
    }

    @GetMapping("/myInfo")
    public String myInfo(){
        return "app/neosUser/myInfo";
    }

    @GetMapping("/chat")
    public String chat(){
        return "app/fix/chattingBase";
    }

    @GetMapping("/chatAll")
    public String chatAll(){
        return "app/fix/chattingAll";
    }

    @GetMapping("/modal")
    public String modal(){
        return "app/neosUser/modalList";
    }



}

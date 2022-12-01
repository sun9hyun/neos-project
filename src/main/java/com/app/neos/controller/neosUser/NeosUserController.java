package com.app.neos.controller.neosUser;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import com.app.neos.service.neosUser.NeosUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/neosUser/*")
@RequiredArgsConstructor
@Slf4j
public class NeosUserController {
    private final NeosUserService neosUserService;

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

//    유저 목록
    @GetMapping("userNeos/list")
    public String List(Model model){
        List<User> userDTOS = neosUserService.findUser();

        model.addAttribute("users",userDTOS);

        return "app/neosUser/neosListAfter";


    }



}

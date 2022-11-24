package com.app.neosproject.controller.login;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/*")
public class LoginController {

    /*로그인 모달 */
    @GetMapping("/login")
    public String login(){
        return "app/loginAndJoin/login";
    }





}

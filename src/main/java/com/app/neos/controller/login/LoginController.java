package com.app.neos.controller.login;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import com.app.neos.service.join.*;
import com.app.neos.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login/*")
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final KaKaoLoginService kaKaoService;
    private final NaverService naverService;
    private final LoginService loginService;
    private final GoogleJoinService googleJoinService;
    /*로그인 모달 */
    @GetMapping("/login")
    public String login(){
        return "app/loginAndJoin/login";
    }


    @GetMapping("/kakao")
    public RedirectView loginOk(@RequestParam String code, HttpServletRequest request){
        String token = kaKaoService.getKaKaoAccessToken(code);

        HttpSession session= (HttpSession)request.getSession();
        session.setAttribute("token",token);

        try {
            Long id = kaKaoService.getKakaoIdByToken(token);
            String realId = id+"k";
            UserDTO userDTO = loginService.login(realId);
            if(userDTO == null){
                return new RedirectView("/main/main?login=KakaoFalse");
            }else {
                if(userDTO.getUserCollegeCertify().equals("false")){
                    return new RedirectView("/main/main?login=need");
                }
                session.setAttribute("loginUser",userDTO.getUserId());
                session.setAttribute("loginUserName",userDTO.getUserNickName());
                session.setAttribute("college",userDTO.getCollegeId());
                session.setAttribute("realId",realId);
                session.setAttribute("userFile",userDTO.getUserFile());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView("/main/main?login=true");
    }


    @GetMapping("/naver/navercallback")
    public RedirectView naverJoin(@RequestParam String code, HttpServletRequest request) {
        String token = naverService.getNaverAccessToken(code);
        HttpSession session= (HttpSession)request.getSession();
        session.setAttribute("token",token);
        try {
            String id = naverService.getNaverIdByToken(token);
            String realId = id+"-naver";
            UserDTO userDTO = loginService.login(realId);
            if(userDTO == null){
                return new RedirectView("/main/main?login=NaverFalse");
            }else {
                if(userDTO.getUserCollegeCertify().equals("false")){
                    return new RedirectView("/main/main?login=need");
                }
                session.setAttribute("loginUser",userDTO.getUserId());
                session.setAttribute("loginUserName",userDTO.getUserNickName());
                session.setAttribute("college",userDTO.getCollegeId());
                session.setAttribute("realId",realId);
                session.setAttribute("userFile",userDTO.getUserFile());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RedirectView("/main/main?login=true");
    }

    @GetMapping("/google")
    public RedirectView googleLogin(@RequestParam String code,HttpSession session) throws Exception{
        String id = googleJoinService.loginInfo(code);
        String realId = id+"-google";
        UserDTO userDTO = loginService.login(realId);
        if(userDTO == null){
            return new RedirectView("/main/main?login=GoogleFalse");
        }else {
            if(userDTO.getUserCollegeCertify().equals("false")){
                return new RedirectView("/main/main?login=need");
            }
            session.setAttribute("loginUser",userDTO.getUserId());
            session.setAttribute("loginUserName",userDTO.getUserNickName());
            session.setAttribute("college",userDTO.getCollegeId());
            session.setAttribute("realId",realId);
            session.setAttribute("userFile",userDTO.getUserFile());
        }

        return new RedirectView("/main/main?login=true");
    }


    @GetMapping("testMain")
    public String test2(){
        return "app/main/Testmain";
    }

}

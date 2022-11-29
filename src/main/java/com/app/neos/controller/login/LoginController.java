package com.app.neos.controller.login;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.service.join.KaKaoLoginService;
import com.app.neos.service.join.KaKaoService;
import com.app.neos.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login/*")
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private  final KaKaoLoginService kaKaoService;
    private final LoginService loginService;
    /*로그인 모달 */
    @GetMapping("/login")
    public String login(){
        return "app/loginAndJoin/login";
    }


    @GetMapping("/kakao")
    public RedirectView loginOk(@RequestParam String code, HttpServletRequest request){
        log.info("들어옴");
        String token = kaKaoService.getKaKaoAccessToken(code);
        log.info(token);

        HttpSession session= (HttpSession)request.getSession();
        try {
            Long id = kaKaoService.getKakaoIdByToken(token);
            String realId = id+"k";
            if(loginService.login(realId) == null){
                return new RedirectView("/main/main?login=false");
            }else {
                UserDTO userDTO = loginService.login(realId);
                session.setAttribute("loginUser",userDTO.getUserId());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView("/main/main?login=true");
    }


}

package com.app.neos.controller.login;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.service.join.KaKaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/logout/*")
public class LogoutController {
    private final KaKaoService kaKaoService;
    private final UserCustomRepository userCustomRepository;


    @GetMapping("logout")
    public RedirectView logout(HttpServletRequest request, RedirectAttributes redirectAttributes){
        HttpSession session = (HttpSession)request.getSession();
          Long id=  (Long)session.getAttribute("loginUser");
        UserDTO userDTO = null;
        if(session.getAttribute("college")==null){
            userDTO = userCustomRepository.findNoCollegeById(id);
        }else{
            userDTO = userCustomRepository.findById(id);
        }
                String oauthId = userDTO.getUserOAuthId();
        if(oauthId.contains("k")){
            kaKaoService.logoutKakao(session.getAttribute("token").toString());
        }
        session.invalidate();
        return new RedirectView("/main/main?logout=true");
    }
}

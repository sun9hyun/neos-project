package com.app.neos.controller.join;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.service.join.JoinService;
import com.app.neos.service.join.KaKaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/join/*")
@RequiredArgsConstructor
@Slf4j
public class JoinController {
    private  final KaKaoService kaKaoService;
    private final JoinService joinService;

    /*회원가입 모달 */
    @GetMapping("/join")
    public String join(){
        return "app/loginAndJoin/join";
    }


    /*회원 가입 페이지*/
    @GetMapping("/join-page-details")
    public String joinPage(UserDTO userDTO){

        return "app/loginAndJoin/joinPage";
    }

    /*이용약관*/
    @GetMapping("/use-clause-list")
    public String edragonClause(){
        return "app/loginAndJoin/edragonClause";
    }

    /*전자 금융 약관*/
    @GetMapping("/electronic-finance-clause-list")
    public String electronicFinanceClause(){
        return "app/loginAndJoin/electronicFinanceClause";
    }

    /*개인 정보 약관 */
    @GetMapping("/individual-clause-list")
    public String individualClause(){
        return "app/loginAndJoin/individualClause";
    }

    @GetMapping("/kakao")
    public RedirectView kakaoJoin(@RequestParam String code, RedirectAttributes redirectAttributes){
        String token = kaKaoService.getKaKaoAccessToken(code);
        try {
            String name = kaKaoService.getKakaoNickNameByToken(token);
            String email = kaKaoService.getKakaoEmailByToken(token);
            Long id = kaKaoService.getKakaoIdByToken(token);
            String realId = id+"k";
            String userProfile = kaKaoService.getKakaoProfileImgByToken(token);
            redirectAttributes.addFlashAttribute("oAuthNickNames",name);
            redirectAttributes.addFlashAttribute("oauthEmails",email);
            redirectAttributes.addFlashAttribute("tokenId",realId);
            redirectAttributes.addFlashAttribute("oAuthUserProfile",userProfile);
            if(joinService.duplicateId(realId)){
                return new RedirectView("/main/main?check=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RedirectView("/join/join-page-details");
    }

    @PostMapping("/join")
    public RedirectView joinOk(UserDTO userDTO){
        joinService.join(userDTO);
        return new RedirectView("/main/main?join=true");
    }



}

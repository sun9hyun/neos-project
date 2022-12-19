package com.app.neos.controller.join;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.service.join.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("/join/*")
@RequiredArgsConstructor
@Slf4j
public class JoinController {
    private final KaKaoService kaKaoService;
    private final NaverService naverService;
    private final GoogleJoinService googleService;
    private final JoinService joinService;

    /*회원가입 모달 */
    @GetMapping("/join")
    public String join(){
        return "app/loginAndJoin/join";
    }


    /*회원 가입 페이지*/
    @GetMapping("/join-page-details")
    public String joinPage(UserDTO userDTO, Model model){
        model.addAttribute("collegeCityList",joinService.getCollegeCityList());
        model.addAttribute("userMajorList",joinService.getMajorList());
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
                return new RedirectView("/main?check=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RedirectView("/join/join-page-details");
    }

    @PostMapping("/join")
    public RedirectView joinOk(UserDTO userDTO,RedirectAttributes redirectAttributes){
        joinService.join(userDTO);
        if(userDTO.getUserCollegeCertify().equals("noNeed")){
            return new RedirectView("/main?join=true");
        }
        redirectAttributes.addAttribute("token", userDTO.getUserOAuthId());
        redirectAttributes.addAttribute("email",userDTO.getUserCollegeEmail());
        return new RedirectView("/send/email");

    }

    @GetMapping("/naver/navercallback")
    public RedirectView naverJoin(@RequestParam String code, RedirectAttributes redirectAttributes){
        String token = naverService.getNaverAccessToken(code);
        String naverId = null;
        String naverName = null;
        String naverPhoneNumber = null;
        String naverEmail = null;
        try {
            naverId =  naverService.getNaverIdByToken(token)+"-naver";
            naverName = naverService.getNaverNameByToken(token);
            naverPhoneNumber = naverService.getNaverMobileByToken(token);
            naverEmail = naverService.getNaverEmailByToken(token);
            redirectAttributes.addFlashAttribute("oAuthNickNames",naverName);
            redirectAttributes.addFlashAttribute("oauthEmails",naverEmail);
            redirectAttributes.addFlashAttribute("tokenId",naverId);
            redirectAttributes.addFlashAttribute("oAuthUserProfile","/images/fix/userBasic.png");
            redirectAttributes.addFlashAttribute("phone",naverPhoneNumber);
            if(joinService.duplicateId(naverId)){
                return new RedirectView("/main?check=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RedirectView("/join/join-page-details");
    }

    @GetMapping("/google")
    public RedirectView googleJoin(@RequestParam String code, RedirectAttributes redirectAttributes) throws Exception{
       ArrayList<String> userinfo =  googleService.getGoogleAccessTokenInfo(code);

        String userEmail = userinfo.get(0);
        String userName = userinfo.get(1);
        String userFile = userinfo.get(2);
        String oauthId = userinfo.get(3)+"-google";
        redirectAttributes.addFlashAttribute("oAuthNickNames",userName);
        redirectAttributes.addFlashAttribute("oauthEmails",userEmail);
        redirectAttributes.addFlashAttribute("tokenId",oauthId);
        redirectAttributes.addFlashAttribute("oAuthUserProfile",userFile);
        if(joinService.duplicateId(oauthId)){
            return new RedirectView("/main?check=true");
        }

        return new RedirectView("/join/join-page-details");
    }

    @GetMapping("/certification")
    public String certification(){
        return "app/main/certificationNeed";
    }


}
package com.app.neos.controller.join;


import com.app.neos.service.join.EmailService;
import com.app.neos.service.join.JoinService;
import com.app.neos.type.user.UserCollegeMajor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/send/*")
@Slf4j
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final JoinService joinService;

    @GetMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String email) throws Exception {
//            log.info(email);
        String confirm = emailService.sendSimpleMessage(email, null);

        return "app/main/TestMain";
    }

    @GetMapping("/email")
    public RedirectView email(String token, String email) throws Exception{
        emailService.sendSimpleMessage(email, token);
        return new RedirectView("/main/main?join=need");
    }



    @GetMapping("/ok")
    public RedirectView emailOk(@RequestParam String token){
        log.info("==================================================");
        log.info(token);
        joinService.certify(token);
        return new RedirectView("/main/main?certify=true");
    }

    @GetMapping("test")
    public void test(UserCollegeMajor grade){
        log.info(grade.toString());
    }
}

package com.app.neos.controller.myPage;

import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.service.myPage.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pay/*")
public class PayController {
    private final PayService payService;

    // 결제 페이지
    @GetMapping("/point")
    public String point(){
        return "app/fix/chargeInput";
    }

    // 결제 후
//    @PostMapping("/charging")
//    public RedirectView charging(NeosPointDTO neosPointDTO){
//        payService.savePoint(neosPointDTO);
//        payService.userPointUpdate(neosPointDTO);
//        return new RedirectView("point");
//    }
}

package com.app.neos.controller.myPage;

import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.service.myPage.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay/*")
public class PayRestController {
    private final PayService payService;

    // 결제 후
    @PostMapping("/chargingOk")
    public String chargingOk(@RequestBody NeosPointDTO neosPointDTO, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        int pay = neosPointDTO.getNeosPointMoney();
        payService.postEXP(userId, pay);
        payService.savePoint(neosPointDTO);
        payService.userPointUpdate(neosPointDTO);
        return "savePoint success";
    }
}

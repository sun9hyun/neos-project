package com.app.neos.controller.store;

import com.app.neos.service.store.StorePurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/store-purchase/*")
public class StorePurchaseRestController {
    private final StorePurchaseService storePurchaseService;

    @GetMapping("/purchase/{storeId}")
    public String purchase(@PathVariable("storeId")Long storeId, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        boolean check = storePurchaseService.purchase(userId, storeId);
        if (check){
            return "duplicated";
        }
        return "success";
    }
}

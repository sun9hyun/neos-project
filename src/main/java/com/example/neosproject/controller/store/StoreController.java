package com.example.neosproject.controller.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store/*")
public class StoreController {
    @GetMapping("/storeList")
    public String storeList(){
        return "app/store/storeList";
    }

    @GetMapping("/storeCreate")
    public String storeCreate(){
        return "app/store/storeCreate";
    }

    @GetMapping("/storeDetail")
    public String storeDetail(){
        return "app/store/storeDetail";
    }
}

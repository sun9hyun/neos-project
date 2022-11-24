package com.app.neosproject.controller.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store/*")
public class StoreController {
//    자료상점 목록
    @GetMapping("/store-list")
    public String storeList(){
        return "app/store/storeList";
    }

//    자료상점 글작성
    @GetMapping("/store-create")
    public String storeCreate(){
        return "app/store/storeCreate";
    }

//    자료상점 상세
    @GetMapping("/store-detail")
    public String storeDetail(){
        return "app/store/storeDetail";
    }
}

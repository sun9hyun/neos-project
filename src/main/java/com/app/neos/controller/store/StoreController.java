package com.app.neos.controller.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.Store;
import com.app.neos.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/store/*")
public class StoreController {
    private final StoreService storeService;

//    자료상점 목록
    @GetMapping("/store-list")
    public String storeList(Model model){
        List<StoreDTO> storeDTOS = storeService.findStore();

        model.addAttribute("stores", storeDTOS);

        return "app/store/storeList";
    }

//    자료상점 글작성
    @GetMapping("/store-create")
    public String storeCreate(StoreDTO storeDTO){
        return "app/store/storeCreate";
    }

    @PostMapping("/store-create")
    public RedirectView saveOk(StoreDTO storeDTO){
//        Store store = storeDTO.toEntity();

        storeService.saveStore(storeDTO);

        return new RedirectView("store-list");
//        return new RedirectView("store-create");
    }


//    자료상점 상세
    @GetMapping("/store-detail")
    public String storeDetail(Long storeId, Model model){
        model.addAttribute("store", storeService.findStoreOne(storeId));
        return "app/store/storeDetail";
    }
}

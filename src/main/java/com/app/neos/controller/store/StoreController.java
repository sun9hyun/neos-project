package com.app.neos.controller.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.neosUser.NeosUserService;
import com.app.neos.service.store.StorePurchaseService;
import com.app.neos.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/store/*")
public class StoreController {
    private final StoreService storeService;
    private final NeosUserService neosUserService;
    private final StorePurchaseService storePurchaseService;
    private final UserRepository userRepository;


//    자료상점 목록
    @GetMapping("/store-list")
    public String storeList(Model model, @PageableDefault(page = 0, size = 12) Pageable pageable) {

        Page<StoreDTO> storeDTOS = storeService.findStorePage(pageable);

        int startPage = Math.max(1, storeDTOS.getPageable().getPageNumber());
        int endPage = Math.min(storeDTOS.getPageable().getPageNumber(), storeDTOS.getTotalPages());

        model.addAttribute("stratPAge", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("stores", storeDTOS);
        model.addAttribute("total", storeService.findStore().size());

        System.out.println("********************* storeDTOS : " + storeDTOS + "*********************");


        return "app/store/storeList";



//        List<StoreDTO> storeDTOS = storeService.findStore();
//        model.addAttribute("stores", storeDTOS);
//        return "app/store/storeList";
    }

//    자료상점 게시글 작성
    @GetMapping("/store-create")
    public String storeCreate(StoreDTO storeDTO){
        return "app/store/storeCreate";
    }

    @PostMapping("/store-create")
    public RedirectView saveOk(StoreDTO storeDTO, HttpSession session){
//        Store store = storeDTO.toEntity();
        Long userId = (Long)session.getAttribute("loginUser");
        storeService.postEXP(userId);
        storeService.saveStore(storeDTO);

        return new RedirectView("store-list");
//        return new RedirectView("store-create");
    }
    

//  자료상점 게시글 업데이트
    @GetMapping("/store-update")
    public String update(Long storeId, Model model){
        model.addAttribute("storeDTO", storeService.findStoreOne(storeId));
        return "app/store/storeUpdate";
    }

    @PostMapping("/store-update")
    public RedirectView storeUpdate(StoreDTO storeDTO, RedirectAttributes redirectAttributes){
        storeService.updateStore(storeDTO);
        redirectAttributes.addAttribute("storeId", storeDTO.getStoreId());
        return new RedirectView("store-detail");
    }


    // 자료상점 게시글 상세
    @GetMapping("/store-detail")
    public String storeDetail(Long storeId, Model model, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        StoreDTO storeDTO = storeService.findStoreOne(storeId);

        User user = userRepository.findById(userId).get();
        String resultUrl = "";

        if (storeDTO.getStoreStatus().toString() == "FREE") {
            model.addAttribute("store", storeDTO);
            model.addAttribute("user", user);

            resultUrl = "app/store/storeDetail-free";
        } else if(storeDTO.getStoreStatus().toString() == "PAY"){
            if (storePurchaseService.checkPurchase(userId, storeId)){
                model.addAttribute("store", storeDTO);
                model.addAttribute("user", user);

                resultUrl = "app/store/storeDetail-purchase";
            }else if(!storePurchaseService.checkPurchase(userId, storeId)){
                model.addAttribute("store", storeDTO);
                model.addAttribute("user", user);

                resultUrl = "app/store/storeDetail-pay";
            }
        }
        return resultUrl;
    }

//    // 자료상점 게시글 상세
//    @GetMapping("/store-detail")
//    public String storeDetail(Long storeId, Model model){
//        model.addAttribute("store", storeService.findStoreOne(storeId));
//        return "app/store/storeDetail";
//    }
    
    // 자료상점 게시글 삭제
    @GetMapping("/store-delete")
    public RedirectView delete(Long storeId, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        storeService.deleteByStoreId(storeId);
        storeService.postDeleteEXP(userId);
        return new RedirectView("store-list");
    }

    // 자료상점 게시글 결제
    @GetMapping("/store-purchase/{storeId}")
    public String purchase(@PathVariable("storeId") Long storeId, Model model, HttpSession session){
        System.out.println("********************* 1 : 들어옴*********************");

        Long userId = (Long)session.getAttribute("loginUser");
        StoreDTO storeDTO = storeService.findStoreOne(storeId);
        model.addAttribute("store", storeDTO);
        boolean check = storePurchaseService.purchase(userId, storeId);
        String resultUrl = "";
        resultUrl = "app/store/storeDetail-purchase";
        System.out.println("********************* 2 : 들어옴*********************");


//        return new RedirectView("store-list");
        return resultUrl;
    }

}

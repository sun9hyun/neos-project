package com.app.neos.controller.myPage;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.service.myPage.MyPageService;
import com.app.neos.service.neosUser.NeosUserService;
import com.app.neos.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-detail/*")
public class MyPageRestController {
    private final MyPageService myPageService;
    private final StoreService storeService;
    private final NeosUserService neosUserService;

    // 마이페이지 자료상점 게시글 조회
    @GetMapping("/store/{userId}")
//    public List<StoreDTO> storeList(@PathVariable("userId") Long userId) {return myPageService.findStoreByUserId(userId);}
    public Slice<StoreDTO> storeList(@PageableDefault(size = 4) Pageable pageable, @PathVariable("userId") Long userId){
        return storeService.findStorePageBySlice(pageable, userId);
    }

    @GetMapping("information/{userId}")
    public UserDTO userInfo(@PathVariable("userId") Long userId){
        return neosUserService.findByUserId(userId);
    }


}




























package com.app.neos.controller.neosUser;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import com.app.neos.repository.neos.NeosUserCustomRepository;
import com.app.neos.service.neosUser.NeosUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/neosUser/*")
public class NeosUserRestController {

    private final NeosUserService neosUserService;
    private final NeosUserCustomRepository neosUserCustomRepository;

//    유저 전체조회
@GetMapping("/neosUserList")
    public Slice<UserDTO> list (@PageableDefault(size = 4 , sort = "updatedDate" , direction = Sort.Direction.DESC) Pageable pageable){

        return neosUserCustomRepository.findAllPage(pageable);
    }

   @PostMapping("{keyword}")            /*검색도 똑같이 넘겨줌 */
   public Slice<UserDTO> list (@PageableDefault(size = 4, sort = "id" , direction = Sort.Direction.DESC) Pageable pageable,@PathVariable("keyword")  String keyword){
        return neosUserService.findBykeyword(keyword,pageable);
   }

}

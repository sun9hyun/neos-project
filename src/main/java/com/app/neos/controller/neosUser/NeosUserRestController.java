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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

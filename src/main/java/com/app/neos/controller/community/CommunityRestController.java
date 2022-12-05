package com.app.neos.controller.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/*")
public class CommunityRestController {
    private final CommunityService communityService;

    //     게시글 전체 조회
    @GetMapping("/community")
    public List<CommunityDTO> list(CommunityDTO communityDTO){

        List<CommunityDTO> communityDTOS = communityService.findAll();
        return communityDTOS;
    }

}

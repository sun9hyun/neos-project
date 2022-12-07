package com.app.neos.controller.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/*")
public class CommunityRestController {
    private final CommunityService communityService;

    //     게시글 전체 조회
    @GetMapping("/communityList")
    public List<CommunityDTO> list(CommunityDTO communityDTO){
        List<CommunityDTO> communityDTOS = communityService.findAll();
        return communityDTOS;
    }

    //     게시글 등록
    @PostMapping("/communityOk")
    public String write(@RequestBody CommunityDTO communityDTO){
        communityService.saveCommunity(communityDTO);
        return "write success";
    }

    //     게시글 하나 조회
//    @GetMapping("/communityDetail")
//    public CommunityDTO detail(Long communityId){
//        CommunityDTO communityDTOS = communityService.findByCommunityId(communityId);
//        return communityDTOS;
//    }

    //     게시글 수정
    @PutMapping("/communityUpdate")
    public String modify(@RequestBody CommunityDTO communityDTO){
        communityService.updateCommunity(communityDTO);
        return "modify success";
    }

//    @PutMapping(value = "/{cno}")
//    public String modify(@RequestBody CommunityDTO communityDTO, @PathVariable("cno") Long communityId){
//        communityDTO.setCommunityId(communityId);
//        communityService.updateCommunity(communityDTO);
//        return "modify success";
//    }

    //     게시글 삭제
    @DeleteMapping("/communityDelete")
    public String delete(@RequestBody CommunityDTO communityDTO){
        communityService.deleteCommunity(communityDTO);
        return "delete success";
    }


}

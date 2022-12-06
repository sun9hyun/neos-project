package com.app.neos.controller.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

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

    //     게시글 작성
    //  consumes:받을 타입, produces:전달 타입
//    @PostMapping(value = "/communityOk", consumes = "application/json", produces = "text/plain; charset=utf-8")
//    public ResponseEntity<String> write(@RequestBody CommunityDTO communityDTO) throws UnsupportedEncodingException {
//        communityService.saveCommunity(communityDTO);
//        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
//    }

    @PostMapping("/communityOk")
    public String write(@RequestBody CommunityDTO communityDTO){
        communityService.saveCommunity(communityDTO);
        return "write success";
    }

    //     게시글 하나 조회
    @GetMapping("/communityDetail")
    public CommunityDTO detail(Long communityId){
        CommunityDTO communityDTOS = communityService.findByCommunityId(communityId);
        return communityDTOS;
    }



}

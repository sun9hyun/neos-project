package com.app.neos.controller.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.CommunityLikeDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.repository.community.CommunityCustomRepository;
import com.app.neos.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/*")
public class CommunityRestController {
    private final CommunityService communityService;
    private final CommunityCustomRepository communityCustomRepository;

    //    @GetMapping("/communityList")
    //    public List<CommunityDTO> list(CommunityDTO communityDTO){
    //        List<CommunityDTO> communityDTOS = communityService.findAll();
    //        return communityDTOS;
    //    }


    //     게시글 전체 조회
    @GetMapping("/communityList")
    public Slice<CommunityDTO> list(@PageableDefault(size = 5, sort = "updatedDate", direction = Sort.Direction.DESC) Pageable pageable){
        return communityCustomRepository.findAllPage(pageable);
    }

    //     게시글 등록
    @PostMapping("/communityOk")
    public String write(@RequestBody CommunityDTO communityDTO, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        communityService.postEXP(userId);
        communityService.saveCommunity(communityDTO);
        return "write success";
    }

    //     게시글 수정
    @PutMapping("/communityUpdate")
    public String modify(@RequestBody CommunityDTO communityDTO){
        communityService.updateCommunity(communityDTO);
        return "modify success";
    }

    //     게시글 삭제
    @DeleteMapping("/communityDelete")
    public String delete(@RequestBody CommunityDTO communityDTO){
        communityService.deleteCommunity(communityDTO);
        return "delete success";
    }

//   ---------------------------------------------------------------------------------------------------------------
    //     게시글 좋아요 수정
    @PutMapping("/communityLikeUpdate")
    public String likeModify(@RequestBody CommunityDTO communityDTO){
        communityService.updateCommunityLike(communityDTO);
        return "likeModify success";
    }

    //    @GetMapping("/communityDetail")
    //    public CommunityDTO detail(Long communityId){
    //        CommunityDTO communityDTOS = communityService.findByCommunityId(communityId);
    //        return communityDTOS;
    //    }

    @GetMapping("/communityLike")
    public List<CommunityLikeDTO> likeList(Long communityId){
        List<CommunityLikeDTO> communityLikeDTOS = communityCustomRepository.findByLikeId(communityId);
        return communityLikeDTOS;
    }

    @GetMapping("/communityLikeList")
    public int LikeCheck(Long communityId, Long userId){
        return communityService.LikeCheck(communityId, userId);
    }




}

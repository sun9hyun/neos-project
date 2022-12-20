package com.app.neos.controller.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.repository.community.CommunityCustomRepository;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/*")
public class CommunityRestController {
    private final CommunityService communityService;
    private final CommunityRepository communityRepository;
    private final CommunityCustomRepository communityCustomRepository;

    //     게시글 전체 조회
    @GetMapping("/communityList")
    public Slice<CommunityDTO> list(@PageableDefault(size = 5, sort = "updatedDate", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        if(userId == null){
            userId = 0L;
        }
        return communityService.findAllPage(pageable, userId);
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
    public String delete(@RequestBody CommunityDTO communityDTO, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        communityService.postDeleteEXP(userId);
        communityService.deleteCommunity(communityDTO);
        return "delete success";
    }

//   ---------------------------------------------------------------------------------------------------------------
    //     게시글 좋아요 수정1
    @PutMapping("/communityLikeUpdate")
    public String likeModify(@RequestBody CommunityDTO communityDTO){
        communityService.updateCommunityLike(communityDTO);
        return "likeModify success";
    }


    //     게시글 좋아요 수정2
    @PostMapping("/communityLike")
    public String like(@RequestBody CommunityDTO communityDTO, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        System.out.println("********************* communityLike : 들어옴*********************" + userId);
        Long communityId = communityDTO.getCommunityId();

        boolean check = communityService.checkLike(userId, communityId);
        System.out.println("********************* communityLike check : 들어옴*********************" + check);

        if (!check){
            communityService.communityLike(userId, communityId);
            System.out.println("********************* communityLike check : if 들어옴 *********************");
        }else {
            communityService.likeCancel(userId, communityId);
            System.out.println("********************* communityLike check : likeCancel 들어옴 *********************");
        }
        return "like success";
    }


    //    @GetMapping("/communityDetail")
    //    public CommunityDTO detail(Long communityId){
    //        CommunityDTO communityDTOS = communityService.findByCommunityId(communityId);
    //        return communityDTOS;
    //    }

//    @GetMapping("/communityLike")
//    public List<CommunityLikeDTO> likeList(Long communityId){
//        List<CommunityLikeDTO> communityLikeDTOS = communityCustomRepository.findByLikeId(communityId);
//        return communityLikeDTOS;
//    }

//    @GetMapping("/communityLikeList")
//    public int LikeCheck(Long communityId, Long userId){
//        return communityService.LikeCheck(communityId, userId);
//    }

//    @GetMapping("/communityList")
    //    public List<CommunityDTO> list(CommunityDTO communityDTO){
    //        List<CommunityDTO> communityDTOS = communityService.findAll();
    //        return communityDTOS;
    //    }


}

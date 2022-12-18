package com.app.neos.controller.community;

import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.entity.community.CommunityReply;
import com.app.neos.repository.community.CommunityReplyRepository;
import com.app.neos.service.community.CommunityReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community-reply")
@Slf4j
public class CommunityReplyRestController {
    private final CommunityReplyService communityReplyService;
    private final CommunityReplyRepository communityReplyRepository;

    // 댓글 목록
    @GetMapping("/reply/{rno}")
    public List<CommunityReplyDTO> replyList(@PathVariable("rno")Long communityId, CommunityReplyDTO communityReplyDTO){
        List<CommunityReplyDTO> communityReplyDTOS = communityReplyService.findReplyAll(communityId);
        return communityReplyDTOS;
    }

    // 댓글 작성
    @PostMapping("/replyOk")
    public String write(@RequestBody CommunityReplyDTO communityReplyDTO, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        communityReplyService.postEXP(userId);
        communityReplyService.saveCommunityReply(communityReplyDTO);
        return "reply write success";
    }

    // 댓글 수정
    @PutMapping("/replyUpdate/{rno}")
    public String modify(@PathVariable("rno") Long CommunityReplyId, CommunityReplyDTO communityReplyDTO){
        communityReplyDTO.setCommunityReplyId(CommunityReplyId);
        log.info("==================================================");
        log.info(communityReplyDTO.toString());
        log.info("==================================================");
        communityReplyService.updateReply(communityReplyDTO);
        return "reply modify success";
    }

    // 댓글 삭제
    @DeleteMapping("replyDelete/{rno}")
    public String delete(@PathVariable("rno") Long replyId, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        communityReplyService.postDeleteEXP(userId);
        communityReplyService.deleteReply(replyId);
        return "reply delete success";
    }

}

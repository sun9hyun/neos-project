package com.app.neos.controller.community;

import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.entity.community.CommunityReply;
import com.app.neos.repository.community.CommunityReplyRepository;
import com.app.neos.service.community.CommunityReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community-reply")
public class CommunityReplyRestController {
    private final CommunityReplyService communityReplyService;
    private final CommunityReplyRepository communityReplyRepository;

    @GetMapping("/reply/{rno}")
    public List<CommunityReplyDTO> replyList(@PathVariable("rno")Long communityId, CommunityReplyDTO communityReplyDTO){
        List<CommunityReplyDTO> communityReplyDTOS = communityReplyService.findReplyAll(communityId);
        return communityReplyDTOS;
    }

    @PostMapping("/replyOk")
    public String write(@RequestBody CommunityReplyDTO communityReplyDTO, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        communityReplyService.postEXP(userId);
        communityReplyService.saveCommunityReply(communityReplyDTO);
        return "reply write success";
    }

    @PutMapping("/replyUpdate/{rno}")
    public String modify(@PathVariable("rno") Long CommunityReplyId, CommunityReplyDTO communityReplyDTO){
        CommunityReplyDTO ReplyDTO = communityReplyDTO;
        ReplyDTO.setCommunityReplyId(CommunityReplyId);
        communityReplyService.updateReply(communityReplyDTO);
        return "reply modify success";
    }

    @DeleteMapping("replyDelete/{rno}")
    public String delete(@PathVariable("rno") Long replyId, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        communityReplyService.postDeleteEXP(userId);
        communityReplyService.deleteReply(replyId);
        return "reply delete success";
    }

}

package com.app.neos.controller.community;

import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.entity.counseling.Counseling;
import com.app.neos.repository.counseling.CounselingReplyRepository;
import com.app.neos.service.counseling.CounselingReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/counseling-reply")
public class CounselingReplyRestController {
    private final CounselingReplyService counselingReplyService;
    private final CounselingReplyRepository counselingReplyRepository;

    @GetMapping("/reply/{rno}")
    public List<CounselingReplyDTO> replyList(@PathVariable("rno") Long counsellingId, CounselingReplyDTO counselingReplyDTO){
        List<CounselingReplyDTO> counselingReplyDTOS = counselingReplyService.findReplyAll(counsellingId);
        return counselingReplyDTOS;
    }

    @PostMapping("/replyOk")
    public String write(@RequestBody CounselingReplyDTO counselingReplyDTO, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        counselingReplyService.postEXP(userId);
        counselingReplyService.saveCounselingReply(counselingReplyDTO);
        return "reply write success";
    }

    @PutMapping("/replyUpdate/{rno}")
    public String modify(@PathVariable("rno")Long CounselingId, CounselingReplyDTO counselingReplyDTO){
        CounselingReplyDTO replyDTO = counselingReplyDTO;
        replyDTO.setCounselingReplyId(CounselingId);
        counselingReplyService.updateReply(counselingReplyDTO);
        return "reply modify success";
    }

    @DeleteMapping("replyDelete/{rno}")
    public String delete(@PathVariable("rno") Long replyId, HttpSession session){
        Long userId = (Long)session.getAttribute("loginUser");
        counselingReplyService.postDeleteEXP(userId);
        counselingReplyService.deleteReply(replyId);
        return "reply delete success";
    }
}

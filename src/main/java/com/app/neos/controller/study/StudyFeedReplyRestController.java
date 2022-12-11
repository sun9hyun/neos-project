package com.app.neos.controller.study;

import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.entity.study.StudyFeedReply;
import com.app.neos.service.study.StudyFeedReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/feed-reply/")
@RequiredArgsConstructor
public class StudyFeedReplyRestController {
    private final StudyFeedReplyService studyFeedReplyService;


    @PostMapping("/{bno}")
    public String post(@PathVariable("bno") Long feedId, StudyFeedReplyDTO studyFeedReplyDTO, Long userId){
        studyFeedReplyService.post(studyFeedReplyDTO,userId,feedId);
        return "sucess";
    }

    @GetMapping("/{bno}")
    public List<StudyFeedReplyDTO> showList(@PathVariable("bno") Long feedId){
        return studyFeedReplyService.show(feedId);
    }

    @DeleteMapping("/{bno}")
    public String deleteReply(@PathVariable("bno") Long replyId){
        studyFeedReplyService.remove(replyId);
        return "success";
    }

    @PutMapping("/{bno}")
    public String updateReply(@PathVariable("bno") Long replyId, StudyFeedReplyDTO studyFeedReplyDTO){
        studyFeedReplyDTO.setStudyFeedReplyId(replyId);
        studyFeedReplyService.update(studyFeedReplyDTO);
        return "success";
    }


}

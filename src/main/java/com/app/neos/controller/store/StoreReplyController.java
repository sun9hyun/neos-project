package com.app.neos.controller.store;

import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.service.store.StoreReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store-reply/*")
public class StoreReplyController {
    private final StoreReplyService storeReplyService;

    // 자료상점 게시글 댓글 등록
    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> write(@RequestBody StoreReplyDTO storeReplyDTO, HttpSession session) throws UnsupportedEncodingException {
        System.out.println("********************* 컨트롤러 : " + storeReplyDTO + "*********************");
        Long userId = (Long)session.getAttribute("loginUser");
        storeReplyService.postEXP(userId);
        storeReplyService.saveReply(storeReplyDTO);
        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
    }

    // 자료상점 게시글 댓글 전체 조회
    @GetMapping("/list/{bno}")
    public List<StoreReplyDTO> list(@PathVariable("bno") Long storeId){
        return storeReplyService.findReply(storeId);
    }

    // 자료상점 게시글 댓글 수정
    @PatchMapping(value = {"/{rno}", "/{rno}/{userId}"})
    public String update(@RequestBody StoreReplyDTO storeReplyDTO, @PathVariable("rno") Long storeReplyId, @PathVariable(value = "userId", required = false) Long userId){
        storeReplyDTO.setStoreReplyId(storeReplyId);
        storeReplyDTO.setUserId(userId);

        System.out.println("********************* 컨트롤러22 : " + storeReplyDTO + "*********************");

        storeReplyService.updateReply(storeReplyDTO);
        return "update success";
    }
    
    // 자료상점 게시글 댓글 삭제
    @DeleteMapping("/{rno}")
    public String delete(@PathVariable("rno") Long storeReplyId, HttpSession session) {
        Long userId = (Long)session.getAttribute("loginUser");
        storeReplyService.postDeleteEXP(userId);
        storeReplyService.deleteByReplyId(storeReplyId);
        return "delete success";
    }

    // 자료상점 게시글 댓글 개수
    @PostMapping("/{bno}")
    public int totalReply(@PathVariable("bno") Long storeId) {
        return storeReplyService.countReply(storeId);
    }

    // 자료상점 게시글 댓글 개별 조회
    @GetMapping("/{rno}")
    public StoreReplyDTO selectReply(@PathVariable("rno") Long storeReplyId) {
        return storeReplyService.findOneReply(storeReplyId);
    }
}

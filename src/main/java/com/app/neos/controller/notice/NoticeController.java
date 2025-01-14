package com.app.neos.controller.notice;

import com.app.neos.domain.notice.NoticeDTO;
import com.app.neos.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notice/*")
public class NoticeController {
    private final NoticeService noticeService;


    @GetMapping("list")
    public String list(Model model, @PageableDefault(page = 0, size = 20) Pageable pageable){

        Page<NoticeDTO> noticeDTOS = noticeService.findNoticePage(pageable);

        int startPage = Math.max(1, noticeDTOS.getPageable().getPageNumber()-1);
        int endPage = Math.min(noticeDTOS.getPageable().getPageNumber()+4, noticeDTOS.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("notices", noticeDTOS);
        model.addAttribute("total", noticeService.findNoticeAll().size());

        return "app/notice/notice";
    }

    @GetMapping("detail")
    public String noticeDetails(Long noticeId, Model model){
        List<NoticeDTO> noticeDTOS = noticeService.findNoticeAll();

        int index = noticeDTOS.indexOf(noticeService.findByNoticeId(noticeId));

        if(index == 0 && noticeDTOS.size() != 1){
            model.addAttribute("nextNoticeDTO", noticeDTOS.get(index+1));
            model.addAttribute("prevNoticeDTO", null);
        } else if(noticeDTOS.size() == 1){
            model.addAttribute("prevNoticeDTO", null);
            model.addAttribute("nextNoticeDTO", null);
        } else if(index == noticeDTOS.size()-1){
            model.addAttribute("prevNoticeDTO", noticeDTOS.get(index-1));
            model.addAttribute("nextNoticeDTO", null);
        } else {
            model.addAttribute("prevNoticeDTO", noticeDTOS.get(index-1));
            model.addAttribute("nextNoticeDTO", noticeDTOS.get(index+1));
        }
        model.addAttribute("noticeDTO", noticeService.findByNoticeId(noticeId));

        return "app/notice/noticeDetails";
    }




}

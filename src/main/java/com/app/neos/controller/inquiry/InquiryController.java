package com.app.neos.controller.inquiry;

import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.entity.inquiry.Inquiry;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.inquiry.InquiryService;
import com.app.neos.service.join.JoinService;
import com.app.neos.service.neosUser.NeosUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/inquiry/*")
@RequiredArgsConstructor
@Slf4j
public class InquiryController {

    private final NeosUserService neosUserService;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final JoinService joinService;
    private final InquiryService inquiryService;





    @PostMapping("inquiry/write")
    public RedirectView saveOk(InquiryDTO inquiryDTO, HttpSession session){
      Long userId = (Long)session.getAttribute("loginUser");
        inquiryService.saveInquiry(inquiryDTO, userId);

        return new RedirectView("list");
    }


    @GetMapping("/list")
    public String listAfter(  Model model, InquiryDTO inquiryDTO , HttpSession session){

        Long id = (Long)session.getAttribute("loginUser");

        //        유저
        model.addAttribute("user" , neosUserService.findByUserId(id) );

        //        문의하기
        model.addAttribute("inquirys" , inquiryService.findbyInquiryId(id));

     /*   if (session != null){
            model.addAttribute("username",session.getId().toString());
        }*/

        return "app/inquiry/inquiryReply";
    }


    @GetMapping("inquiry/delete")
    public RedirectView deleteById(Long inquiryId){
        inquiryService.remove(inquiryId);
        return new RedirectView("list");


    }



}

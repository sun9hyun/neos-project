package com.app.neos.controller.admin;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.entity.college.College;
import com.app.neos.service.admin.AdminCollegeService;
import com.app.neos.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/test/*")
public class AdminTestController {
    private final AdminService adminService;

//    대학교 추가
    @GetMapping("college/write")
    public String save(CollegeDTO collegeDTO){
        return "app/admin/universeWrite";
    }

    @PostMapping("college/write")
    public RedirectView saveOk(CollegeDTO collegeDTO){
        College college = collegeDTO.toEntity();
        adminService.saveCollege(college);

        return new RedirectView("list");
    }

//    대학교 목록
    @GetMapping("college/list")
    public String list(Model model){
        List<CollegeDTO> collegeDTOS = adminService.findCollege();

//        Pageable pageable = PageRequest.of(0,6);
//        Page<CollegeDTO> page = adminService.findCollegePage(pageable);
//
//        model.addAttribute("pages", page);
        model.addAttribute("colleges", collegeDTOS);

        return "app/admin/universeList";
    }

//    대학교 상세보기
    @GetMapping("college/detail")
    public String detail(Long collegeId, Model model){
        model.addAttribute("college",  adminService.findByCollegeId(collegeId));
        model.addAttribute("count", adminService.countByUser(collegeId));
        return "app/admin/universeDetail";
    }


}

package com.app.neos.controller.admin;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.entity.college.College;
import com.app.neos.service.admin.AdminCollegeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/test/*")
public class AdminTestController {
    private final AdminCollegeService adminCollegeService;

//    대학교 추가
    @GetMapping("write")
    public String save(CollegeDTO collegeDTO){
        return "app/admin/universeWrite";
    }

    @PostMapping("write")
    public String saveOk(CollegeDTO collegeDTO){
        College college = collegeDTO.toEntity();
        adminCollegeService.saveCollege(college);

        return "app/admin/universeList";
    }

//    대학교 목록


}

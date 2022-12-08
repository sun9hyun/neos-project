package com.app.neos.controller.admin;

import com.app.neos.domain.Admin.AdminUserDTO;
import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.notice.NoticeDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.banner.Banner;
import com.app.neos.entity.college.College;
import com.app.neos.entity.notice.Notice;
import com.app.neos.entity.user.User;
import com.app.neos.service.admin.AdminCollegeService;
import com.app.neos.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    public String list(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){

        Page<CollegeDTO> collegeDTOS = adminService.findCollegePage(pageable);

        int startPage = Math.max(1, collegeDTOS.getPageable().getPageNumber());
        int endPage = Math.min(collegeDTOS.getPageable().getPageNumber(), collegeDTOS.getTotalPages());
//
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("colleges", collegeDTOS);
        model.addAttribute("total", adminService.findCollege().size());

        return "app/admin/universeList";
    }

//    대학교 상세보기
    @GetMapping("college/detail")
    public String detail(Long collegeId, Model model){
        model.addAttribute("college",  adminService.findByCollegeId(collegeId));
        model.addAttribute("count", adminService.countByUser(collegeId));
        return "app/admin/universeDetail";
    }

//    대학교 수정하기
    @PostMapping("college/update")
    @Transactional
    public RedirectView update(CollegeDTO collegeDTO){

        College college = adminService.findCollegeEntity(collegeDTO.getCollegeId());
        college.update(collegeDTO);

        return new RedirectView("list");
    }


//    대학교 체크 여부에 따라 삭제하기
    @GetMapping("college/deleteCheck")
    public RedirectView deleteByCheck(String collegeIds){
        adminService.deleteByCheck(collegeIds);
        return new RedirectView("list");
    }

    @GetMapping("college/delete")
    public RedirectView deleteById(String collegeId){
        adminService.deleteById(collegeId);
        return new RedirectView("list");
    }



//    배너 목록으로 가기
    @GetMapping("banner/list")
    public String bannerList(Model model){
        model.addAttribute("banners", adminService.findBanner());
        return "app/admin/configBanner";
    }

//    배너 등록으로 가기
    @GetMapping("banner/edit")
    public String save(BannerDTO bannerDTO){
        return "app/admin/bannerEdit";
    }


    @PostMapping("banner/edit")
    public RedirectView saveOk(BannerDTO bannerDTO){
        Banner banner = bannerDTO.toEntity();
        adminService.saveBanner(banner);

        return new RedirectView("list");
    }

//    배너수정가기
    @GetMapping("banner/update")
    public String update(Long bannerId, Model model){
        model.addAttribute("bannerDTO", adminService.findByBannerId(bannerId));
        return "app/admin/bannerUpdate";
    }

    @PostMapping("banner/update")
    @Transactional
    public RedirectView updateOk(BannerDTO bannerDTO){
        Banner banner = adminService.findByBannerEntityId(bannerDTO.getBannerId());
        banner.update(bannerDTO);

        return new RedirectView("list");
    }

    @GetMapping("banner/delete")
    public RedirectView deleteByBannerId(Long bannerId){
        adminService.deleteByBannerId(bannerId);
        return new RedirectView("list");
    }


//    공지사항 등록
    @GetMapping("notice/edit")
    public String save(NoticeDTO noticeDTO){
        return "app/admin/noticePost";
    }

    @PostMapping("notice/edit")
    public RedirectView saveOk(NoticeDTO noticeDTO){
        Notice notice = noticeDTO.toEntity();
        adminService.saveNotice(notice);

        return new RedirectView("edit");
    }


//    유저 목록
    @GetMapping("user/list")
    public String userList(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){

        Page<UserDTO> users = adminService.findAllUserPage(pageable);
        List<AdminUserDTO> counts = new ArrayList<>();

        for (UserDTO user: users) {
            counts.add(adminService.workCount(user.getUserId()));
        }

        int startPage = Math.max(1, users.getPageable().getPageNumber()-1);
        int endPage = Math.min(users.getPageable().getPageNumber()+4, users.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("users", users);
        model.addAttribute("total", users.getTotalElements());

        model.addAttribute("counts", counts);

        return "app/admin/userList";
    }

//    유저 상세
    @GetMapping("user/detail")
    public String userDetail(Long userId, Model model){

        model.addAttribute("user", adminService.findByUserDTOId(userId));
        model.addAttribute("count", adminService.workCount(userId));

        return "app/admin/userDetail";
    }

    //    유저 체크 여부에 따라 삭제하기
    @GetMapping("user/deleteCheck")
    public RedirectView deleteByCheckUser(String userIds){
        adminService.deleteByUserCheck(userIds);
        return new RedirectView("list");
    }

    @GetMapping("user/delete")
    public RedirectView deleteByIdUser(String userId){
        adminService.deleteByUserId(userId);
        return new RedirectView("list");
    }



}

package com.app.neos.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
    @GetMapping("/index")
    public String index(){
        return "app/admin/adminIndex";
    }

    /*배너 관리 클릭시*/
    @GetMapping("/config-banner")
    public String configBanner(){
        return "app/admin/configBanner";
    }

    //    배너 등록
    @GetMapping("/banner-edit")
    public String bannerEditer(){
        return "app/admin/bannerEdit";
    }

    // 공지사항
    @GetMapping("/notice-post")
    public String noticePost(){return "app/admin/noticePost";}

    //게시글관리
    @GetMapping("/study")
    public String studyManage(){return "app/admin/studyManagement";}
    //자유게시판관리
    @GetMapping("/free")
    public String freeManage(){return "app/admin/freeBoardManagement";}
    //고민상담게시판관리
    @GetMapping("/counseling")
    public String counselingManage(){return "app/admin/counselingBoardManagement";}

    //자료상점 게시판 관리
    @GetMapping("/shop")
    public String shopManage(){return "app/admin/freeShopBoardManagement";}

    //문의글 관리
    @GetMapping("/inquiry")
    public String inquiryManage(){return "app/admin/inquiryList";}


    //스터디 댓글관리
    @GetMapping("/study-comment")
    public String studyComment(){return "app/admin/studyComment";}
    //자유게시판 댓글관리
    @GetMapping("/free-comment")
    public String freeComment(){return "app/admin/freeBoardComment";}
    //고민상담 게시판 댓글관리
    @GetMapping("/counseling-comment")
    public String counselingComment(){return "app/admin/counComment";}
    //자유 상점 게시판 댓글관리
    @GetMapping("/shop-comment")
    public String shopComment(){return "app/admin/shopComment";}


    //사용자 목록
    @GetMapping("/user-list")
    public String userList(){return "app/admin/userList";}

    //사용자 상세
    @GetMapping("/user-detail")
    public String userDetail(){return "app/admin/userDetail";}
    //대학교 목록
    @GetMapping("/universe-list")
    public String universeList(){return "app/admin/universeList";}
    //대학교 등록
    @GetMapping("/universe-register")
    public String universeRegister(){return "app/admin/universeDetail";}




















}

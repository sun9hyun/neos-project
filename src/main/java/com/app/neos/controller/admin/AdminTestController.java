package com.app.neos.controller.admin;

import com.app.neos.domain.Admin.AdminUserDTO;
import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.domain.notice.NoticeDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.banner.Banner;
import com.app.neos.entity.college.College;
import com.app.neos.entity.notice.Notice;
import com.app.neos.entity.user.User;
import com.app.neos.service.admin.AdminCollegeService;
import com.app.neos.service.admin.AdminService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import static com.app.neos.entity.user.QUser.user;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/test/*")
public class AdminTestController {
    private final AdminService adminService;
    private final JPAQueryFactory jpaQueryFactory;

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
        model.addAttribute("endPage", collegeDTOS.getTotalPages());
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

        return new RedirectView("/admin/test/index");
    }


//    유저 목록
    @GetMapping("user/list")
    public String userList(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){

        List<UserDTO> userDTOS = jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userNickName,
                user.userOAuthId,
                user.userOAuthEmail,
                user.userCollegeEmail,
                user.userPhoneNumber,
                user.userCollegeCertify,
                user.userCollegeInfo.userCollegeYear,
                user.userCollegeInfo.userCollegeMajor,
                user.userNeosPower.userNeosBadge,
                user.userNeosPower.userNeosPowerLevel,
                user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,
                user.userChattingPoint,
                user.userLike.userO2o,
                user.userLike.userCity,
                user.userLike.userDay,
                user.userLike.userTime,
                user.userMBTI.userMbtiName,
                user.userMBTI.userMbtiColor,
                user.userIntroduce,
                user.userFile,
                user.createdDate
        ))
                .from(user)
                .orderBy(user.userId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        for (UserDTO user: userDTOS) {
            user.setCounts(adminService.workCount(user.getUserId()));
        }


        long total = jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userNickName,
                user.userOAuthId,
                user.userOAuthEmail,
                user.userCollegeEmail,
                user.userPhoneNumber,
                user.userCollegeCertify,
                user.userCollegeInfo.userCollegeYear,
                user.userCollegeInfo.userCollegeMajor,
                user.userNeosPower.userNeosBadge,
                user.userNeosPower.userNeosPowerLevel,
                user.userNeosPower.userNeosPowerAbility,
                user.userNeosPoint,
                user.userChattingPoint,
                user.userLike.userO2o,
                user.userLike.userCity,
                user.userLike.userDay,
                user.userLike.userTime,
                user.userMBTI.userMbtiName,
                user.userMBTI.userMbtiColor,
                user.userIntroduce,
                user.userFile,
                user.createdDate
        ))
                .from(user)
                .fetch().size();

        Page<UserDTO> users = new PageImpl<>(userDTOS,pageable,total);


        int startPage = Math.max(1, users.getPageable().getPageNumber()-1);
        int endPage = Math.min(users.getPageable().getPageNumber()+4, users.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", users.getTotalPages());

        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("users", users);
        model.addAttribute("total", users.getTotalElements());


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



//    관리자 홈
    @GetMapping("index")
    public String index(Model model){

        model.addAttribute("users", adminService.findMainUser());
        model.addAttribute("studies", adminService.findMainStudy());
        model.addAttribute("inquiries", adminService.findMainInquiry());
        model.addAttribute("colleges", adminService.findMainCollege());

        return "app/admin/adminIndex";
    }



    //    스터디 게시글 관리
    @GetMapping("study/list")
    public String study(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){

        Page<StudyDTO> studyDTOS = adminService.findStudyPage(pageable);

        int startPage = Math.max(1, studyDTOS.getPageable().getPageNumber());
        int endPage = Math.min(studyDTOS.getPageable().getPageNumber(), studyDTOS.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", studyDTOS.getTotalPages());
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("studies", studyDTOS);
        model.addAttribute("total", adminService.findAllStudy().size());

        return "app/admin/studyManagement";
    }

    //    스터디 체크 여부에 따라 삭제하기
    @GetMapping("study/deleteCheck")
    public RedirectView deleteByCheckStudy(String studyIds){
        adminService.deleteByStudyCheck(studyIds);
        return new RedirectView("list");
    }

    @GetMapping("study/delete")
    public RedirectView deleteByIdStudy(String studyId){
        adminService.deleteByStudyId(studyId);
        return new RedirectView("list");
    }







    //    자유게시판 게시글 관리
    @GetMapping("community/list")
    public String community(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){

        Page<CommunityDTO> communityDTOS = adminService.findCommunityPage(pageable);


        int startPage = Math.max(1, communityDTOS.getPageable().getPageNumber());
        int endPage = Math.min(communityDTOS.getPageable().getPageNumber(), communityDTOS.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", communityDTOS.getTotalPages());
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("communities", communityDTOS);
        model.addAttribute("total", adminService.findAllCommunity().size());

        return "app/admin/freeBoardManagement";
    }

    //    게시판 체크 여부에 따라 삭제하기
    @GetMapping("community/deleteCheck")
    public RedirectView deleteByCheckCommunity(String communityIds){
        adminService.deleteByCommunityCheck(communityIds);
        return new RedirectView("list");
    }

    @GetMapping("community/delete")
    public RedirectView deleteByIdCommunity(String communityId){
        adminService.deleteByCommunityId(communityId);
        return new RedirectView("list");
    }

    //    고민 상담 게시글 관리
    @GetMapping("counseling/list")
    public String counseling(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<CounselingDTO> counselingDTOS = adminService.findCounselingPage(pageable);

        int startPage = Math.max(1, counselingDTOS.getPageable().getPageNumber());
        int endPage = Math.min(counselingDTOS.getPageable().getPageNumber(), counselingDTOS.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", counselingDTOS.getTotalPages());
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("counselings", counselingDTOS);
        model.addAttribute("total", adminService.findAllCounseling().size());


        return "app/admin/counselingBoardManagement";
    }

    //    고민게시판 체크 여부에 따라 삭제하기
    @GetMapping("counseling/deleteCheck")
    public RedirectView deleteByCheckCounseling(String counselingIds){
        adminService.deleteByCounselingCheck(counselingIds);
        return new RedirectView("list");
    }

    @GetMapping("counseling/delete")
    public RedirectView deleteByIdCounseling(String counselingId){
        adminService.deleteByCounselingId(counselingId);
        return new RedirectView("list");
    }


    //    자료 상점 게시글 관리
    @GetMapping("store/list")
    public String store(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<StoreDTO> storeDTOS = adminService.findStorePage(pageable);

        for (StoreDTO storeDTO : storeDTOS) {
            storeDTO.setStoreFlieDTOS(adminService.findStoreFileByStoreId(storeDTO.getStoreId()));
        }

        int startPage = Math.max(1, storeDTOS.getPageable().getPageNumber());
        int endPage = Math.min(storeDTOS.getPageable().getPageNumber(), storeDTOS.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", storeDTOS.getTotalPages());
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("stores", storeDTOS);
        model.addAttribute("total", adminService.findAllStore().size());

        return "app/admin/freeShopBoardManagement";
    }

    //    자료상점 체크 여부에 따라 삭제하기
    @GetMapping("store/deleteCheck")
    public RedirectView deleteByCheckStore(String storeIds){
        adminService.deleteByStoreCheck(storeIds);
        return new RedirectView("list");
    }

    @GetMapping("store/delete")
    public RedirectView deleteByIdStore(String storeId){
        adminService.deleteByStoreId(storeId);
        return new RedirectView("list");
    }


//    스터디 댓글 목록 조회
    @GetMapping("study/reply/list")
    public String studyReply(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<StudyFeedReplyDTO> studyFeedReplyDTOS = adminService.findStudyRePage(pageable);

        int startPage = Math.max(1, studyFeedReplyDTOS.getPageable().getPageNumber());
        int endPage = Math.min(studyFeedReplyDTOS.getPageable().getPageNumber(), studyFeedReplyDTOS.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", studyFeedReplyDTOS.getTotalPages());
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("studyFeedReplies", studyFeedReplyDTOS);
        model.addAttribute("total", adminService.findAllStudyRe().size());

        return "app/admin/studyComment";
    }

    //    스터디 댓글 체크 여부에 따라 삭제하기
    @GetMapping("study/reply/deleteCheck")
    public RedirectView deleteByCheckStudyFeedReply(String studyFeedReplyIds){
        adminService.deleteByStudyReCheck(studyFeedReplyIds);
        return new RedirectView("list");
    }

    @GetMapping("study/reply/delete")
    public RedirectView deleteByIdStudyFeedReply(String studyFeedReplyId){
        adminService.deleteByStudyReId(studyFeedReplyId);
        return new RedirectView("list");
    }


    //    자유 게시판 댓글 목록 조회
    @GetMapping("community/reply/list")
    public String communityReply(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<CommunityReplyDTO> communityReplyDTOS = adminService.findCommunityRePage(pageable);

        int startPage = Math.max(1, communityReplyDTOS.getPageable().getPageNumber());
        int endPage = Math.min(communityReplyDTOS.getPageable().getPageNumber(), communityReplyDTOS.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", communityReplyDTOS.getTotalPages());
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("communityReplies", communityReplyDTOS);
        model.addAttribute("total", adminService.findAllCommunityRe().size());

        return "app/admin/freeBoardComment";
    }

    //    자유 게시판 댓글 체크 여부에 따라 삭제하기
    @GetMapping("community/reply/deleteCheck")
    public RedirectView deleteByCheckCommunityReply(String communityReplyIds){
        adminService.deleteByCommunityReCheck(communityReplyIds);
        return new RedirectView("list");
    }

    @GetMapping("community/reply/delete")
    public RedirectView deleteByIdCommunityReply(String communityReplyId){
        adminService.deleteByCommunityReId(communityReplyId);
        return new RedirectView("list");
    }

    //    고민 상담 게시판 댓글 목록 조회
    @GetMapping("counseling/reply/list")
    public String counselingReply(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<CounselingReplyDTO> counselingReplyDTOS = adminService.findCounselingRePage(pageable);

        int startPage = Math.max(1, counselingReplyDTOS.getPageable().getPageNumber());
        int endPage = Math.min(counselingReplyDTOS.getPageable().getPageNumber(), counselingReplyDTOS.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", counselingReplyDTOS.getTotalPages());
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("counselingReplies", counselingReplyDTOS);
        model.addAttribute("total", adminService.findAllCounselingRe().size());


        return "app/admin/counComment";
    }

    //    고민 게시판 댓글 체크 여부에 따라 삭제하기
    @GetMapping("counseling/reply/deleteCheck")
    public RedirectView deleteByCheckCounselingReply(String counselingReplyIds){
        adminService.deleteByCounselingReCheck(counselingReplyIds);
        return new RedirectView("list");
    }

    @GetMapping("counseling/reply/delete")
    public RedirectView deleteByIdCounselingReply(String counselingReplyId){
        adminService.deleteByCounselingReId(counselingReplyId);
        return new RedirectView("list");
    }


    //    자료 상점 댓글 목록 조회
    @GetMapping("store/reply/list")
    public String storeReply(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<StoreReplyDTO> storeReplyDTOS = adminService.findStoreRePage(pageable);

        int startPage = Math.max(1, storeReplyDTOS.getPageable().getPageNumber());
        int endPage = Math.min(storeReplyDTOS.getPageable().getPageNumber(), storeReplyDTOS.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", storeReplyDTOS.getTotalPages());
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("storeReplies", storeReplyDTOS);
        model.addAttribute("total", adminService.findAllStoreRe().size());
        return "app/admin/shopComment";
    }

    //    고민 게시판 댓글 체크 여부에 따라 삭제하기
    @GetMapping("store/reply/deleteCheck")
    public RedirectView deleteByCheckStoreReply(String storeReplyIds){
        adminService.deleteByStoreReCheck(storeReplyIds);
        return new RedirectView("list");
    }

    @GetMapping("store/reply/delete")
    public RedirectView deleteByIdStoreReply(String storeReplyId){
        adminService.deleteByStoreReId(storeReplyId);
        return new RedirectView("list");
    }


    //    문의하기 리스트 목록 조회
    @GetMapping("inquiry/list")
    public String inquiry(Model model, @PageableDefault(page = 0, size = 5) Pageable pageable, InquiryDTO inquiryDTO){
        Page<InquiryDTO> inquiryDTOS = adminService.findInquiryPage(pageable);

        int startPage = Math.max(1, inquiryDTOS.getPageable().getPageNumber());
        int endPage = Math.min(inquiryDTOS.getPageable().getPageNumber(), inquiryDTOS.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", inquiryDTOS.getTotalPages());
        model.addAttribute("nowPage", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("inquiries", inquiryDTOS);
        model.addAttribute("total", adminService.findInquiry().size());
        return "app/admin/inquiryList";
    }

    //    문의하기 댓글 체크 여부에 따라 삭제하기
    @GetMapping("inquiry/deleteCheck")
    public RedirectView deleteByCheckInquiry(String inquiryIds){
        adminService.deleteByInquiryCheck(inquiryIds);
        return new RedirectView("list");
    }

    @GetMapping("inquiry/delete")
    public RedirectView deleteByIdInquiry(String inquiryId){
        adminService.deleteByInquiryId(inquiryId);
        return new RedirectView("list");
    }






}

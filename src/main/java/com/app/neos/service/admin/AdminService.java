package com.app.neos.service.admin;

import com.app.neos.domain.Admin.AdminUserDTO;
import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.banner.Banner;
import com.app.neos.entity.college.College;
import com.app.neos.entity.notice.Notice;
import com.app.neos.entity.user.User;
import com.app.neos.repository.admin.*;
import com.app.neos.repository.banner.BannerCustomRepository;
import com.app.neos.repository.banner.BannerRepository;
import com.app.neos.repository.college.CollegeCustomRepository;
import com.app.neos.repository.college.CollegeCustomRepositoryImpl;
import com.app.neos.repository.college.CollegeRepository;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.repository.counseling.CounselingRepository;
import com.app.neos.repository.notice.NoticeCustomRepository;
import com.app.neos.repository.notice.NoticeRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.neos.entity.user.QUser.user;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final CollegeRepository collegeRepository;
    private final CollegeCustomRepository collegeCustomRepository;
    private final BannerRepository bannerRepository;
    private final BannerCustomRepository bannerCustomRepository;
    private final NoticeRepository noticeRepository;
    private final UserCustomRepository userCustomRepository;

    private final AdminCustomRepository adminCustomRepository;
    private final AdminStoreRepository adminStoreRepository;
    private final AdminStudyRepository adminStudyRepository;
    private final AdminCommunityRepository adminCommunityRepository;
    private final AdminCounselingRepository adminCounselingRepository;
    private final AdminInquiryRepository adminInquiryRepository;

    private final AdminStudyFeedReplyRepository adminStudyFeedReplyRepository;
    private final AdminCommunityReplyRepository adminCommunityReplyRepository;
    private final AdminCommunityReReplyRepository adminCommunityReReplyRepository;
    private final AdminCounselingReplyRepository adminCounselingReplyRepository;
    private final AdminCounselingReReplyRepository adminCounselingReReplyRepository;
    private final AdminStoreReplyRepository adminStoreReplyRepository;
    private final AdminStoreReReplyRepository adminStoreReReplyRepository;





    //    대학교 추가
    public void saveCollege(College college){
        collegeRepository.save(college);
    }

    //    대학교 목록
    public List<CollegeDTO> findCollege(){
        return collegeCustomRepository.findAll();
    }

    //    대학교 목록 페이징 처리
    public Page<CollegeDTO> findCollegePage(Pageable pageable){
        return collegeCustomRepository.findAllPage(pageable);
    }

    //    대학교 상세 보기
    public CollegeDTO findByCollegeId(Long collegeId){
        return collegeCustomRepository.findByCollegeId(collegeId);
    }

    //    대학교 관련 유저 수
    public int countByUser(Long collegeId){
        return collegeCustomRepository.countByUser(collegeId);
    }


    //    대학교 엔티티 아이디로 조회
    public College findCollegeEntity(Long collegeId){
        return collegeRepository.findById(collegeId).get();
    }

    //    대학 체크 여부에 따라 삭제하기
    public void deleteByCheck(String collegeIds){
        String[] arCollegeIds = collegeIds.split(",");

        for (int i = 0; i < arCollegeIds.length; i++){
            collegeRepository.deleteById(Long.parseLong(arCollegeIds[i]));
        }
    }

    //    대학교 아이디 하나로 삭제
    public void deleteById(String collegeId){
        collegeRepository.deleteById(Long.parseLong(collegeId));
    }



    //    배너 등록
    public void saveBanner(Banner banner){
        bannerRepository.save(banner);
    }

    //    배너 전체 목록 조회
    public List<BannerDTO> findBanner(){
        return bannerCustomRepository.findAll();
    }

    //    배너 아이디로 조회
    public BannerDTO findByBannerId(Long bannerId){
        return bannerCustomRepository.findById(bannerId);
    }

    //    배너 아이디로 엔티티 조회
    public Banner findByBannerEntityId(Long bannerId){
        return bannerRepository.findById(bannerId).get();
    }

    //    배너 삭제
    public void deleteByBannerId(Long bannerId){
        bannerRepository.deleteById(bannerId);
    }





    //    공지사항 등록
    public void saveNotice(Notice notice){
        noticeRepository.save(notice);
    }


    //    스터디 목록 페이징 처리
    public Page<StudyDTO> findStudyPage(Pageable pageable){
        return adminCustomRepository.findAllStudyPage(pageable);
    }








//    //    유저 목록 페이지
//    public Page<UserDTO> findAllUserPage(Pageable pageable){
//        List<UserDTO> userDTOS = jpaQueryFactory.select(new QUserDTO(
//                user.userId,
//                user.userNickName,
//                user.userOAuthId,
//                user.userOAuthEmail,
//                user.userCollegeEmail,
//                user.userPhoneNumber,
//                user.userCollegeCertify,
//                user.userCollegeInfo.userCollegeYear,
//                user.userCollegeInfo.userCollegeMajor,
//                user.userNeosPower.userNeosBadge,
//                user.userNeosPower.userNeosPowerLevel,
//                user.userNeosPower.userNeosPowerAbility,
//                user.userNeosPoint,
//                user.userChattingPoint,
//                user.userLike.userO2o,
//                user.userLike.userCity,
//                user.userLike.userDay,
//                user.userLike.userTime,
//                user.userMBTI.userMbtiName,
//                user.userMBTI.userMbtiColor,
//                user.userIntroduce,
//                user.userFile,
//                user.createdDate
//        ))
//                .from(user)
//                .orderBy(user.userId.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        for (UserDTO user: userDTOS) {
//            user.setCounts(adminService.workCount(user.getUserId()));
//        }
//
//
//        long total = jpaQueryFactory.select(new QUserDTO(
//                user.userId,
//                user.userNickName,
//                user.userOAuthId,
//                user.userOAuthEmail,
//                user.userCollegeEmail,
//                user.userPhoneNumber,
//                user.userCollegeCertify,
//                user.userCollegeInfo.userCollegeYear,
//                user.userCollegeInfo.userCollegeMajor,
//                user.userNeosPower.userNeosBadge,
//                user.userNeosPower.userNeosPowerLevel,
//                user.userNeosPower.userNeosPowerAbility,
//                user.userNeosPoint,
//                user.userChattingPoint,
//                user.userLike.userO2o,
//                user.userLike.userCity,
//                user.userLike.userDay,
//                user.userLike.userTime,
//                user.userMBTI.userMbtiName,
//                user.userMBTI.userMbtiColor,
//                user.userIntroduce,
//                user.userFile,
//                user.createdDate
//        ))
//                .from(user)
//                .fetch().size();
//
//        return new PageImpl<>(userDTOS,pageable,total);
//    }

    //    유저 아이디로 조회
    public User findByUserId(Long userId){
        return  adminCustomRepository.findByUserId(userId);
    }

    //    유저 아이디로 조회
    public UserDTO findByUserDTOId(Long userId){
        return  adminCustomRepository.findByUserDTOId(userId);
    }

    //    유저 아이디로 게시글 숫자 카운트 뿌리기
    public AdminUserDTO workCount(Long userId){
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        int replyCount = 0;

        int studyFeedReply = adminStudyFeedReplyRepository.findAllByStudyFeedReplyWriter_UserId(userId).size();
        int communityReply = adminCommunityReplyRepository.findByUser_UserId(userId).size();
        int communityReReply = adminCommunityReReplyRepository.findByUser_UserId(userId).size();
        int counselingReply = adminCounselingReplyRepository.findByUser_UserId(userId).size();
        int counselingReReply = adminCounselingReReplyRepository.findByUser_UserId(userId).size();
        int storeReply = adminStoreReplyRepository.findByUser_UserId(userId).size();
        int storeReReply = adminStoreReReplyRepository.findByUser_UserId(userId).size();

        replyCount = studyFeedReply + communityReply + communityReReply + counselingReply + counselingReReply + storeReply + storeReReply;


        adminUserDTO.setStoreCount((long) adminStoreRepository.findByUser_UserId(userId).size());
        adminUserDTO.setStudyCount((long) adminStudyRepository.findByUser_UserId(userId).size());
        adminUserDTO.setCommunityCount((long) adminCommunityRepository.findByUser_UserId(userId).size());
        adminUserDTO.setCounselingCount((long) adminCounselingRepository.findByUser_UserId(userId).size());
        adminUserDTO.setReplyCount((long) replyCount);
        adminUserDTO.setInquiryCount((long) adminInquiryRepository.findByUser_UserId(userId).size());

        return adminUserDTO;
    }

    //    유저 삭제
    public void deleteByUserId(String userId){
        userRepository.deleteById(Long.parseLong(userId));
    }

    //    유저 체크 여부에 따라 삭제하기
    public void deleteByUserCheck(String userIds){
        String[] arUserIds = userIds.split(",");

        for (int i = 0; i < arUserIds.length; i++){
            userRepository.deleteById(Long.parseLong(arUserIds[i]));
        }
    }



}

package com.app.neos.service.admin;

import com.app.neos.domain.Admin.AdminUserDTO;
import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.store.StoreFlieDTO;
import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudyFeedReplyDTO;
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
import com.app.neos.repository.community.CommunityReplyRepository;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.repository.counseling.CounselingReplyRepository;
import com.app.neos.repository.counseling.CounselingRepository;
import com.app.neos.repository.inquiry.InquiryRepository;
import com.app.neos.repository.notice.NoticeCustomRepository;
import com.app.neos.repository.notice.NoticeRepository;
import com.app.neos.repository.store.StoreReplyRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.study.StudyFeedReplyRepository;
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

    private final AdminStudyFollowRepository adminStudyFollowRepository;
    private final StudyRepository studyRepository;
    private final CommunityRepository communityRepository;
    private final CounselingRepository counselingRepository;
    private final StoreRepository storeRepository;

    private final StudyFeedReplyRepository studyFeedReplyRepository;
    private final CommunityReplyRepository communityReplyRepository;
    private final CounselingReplyRepository counselingReplyRepository;
    private final StoreReplyRepository storeReplyRepository;
    private final InquiryRepository inquiryRepository;





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


    //    스터디 목록 페이징 처리
    public Page<StudyDTO> findStudyPage(Pageable pageable){
        Page<StudyDTO> studyDTOS = adminCustomRepository.findAllStudyPage(pageable);

        for (StudyDTO studyDTO : studyDTOS) {
            studyDTO.setFollowTotal(adminStudyFollowRepository.countByStudyStudyId(studyDTO.getStudyId()));
        }

        return studyDTOS;
    }

    //    스터디 목록 조회
    public List<StudyDTO> findAllStudy(){
        return  adminCustomRepository.findAllStudy();
    }


    //    스터디 삭제
    public void deleteByStudyId(String studyId){
        studyRepository.deleteById(Long.parseLong(studyId));
    }

    //    스터디 체크 여부에 따라 삭제하기
    public void deleteByStudyCheck(String studyIds){
        String[] arStudyIds = studyIds.split(",");

        for (int i = 0; i < arStudyIds.length; i++){
            studyRepository.deleteById(Long.parseLong(arStudyIds[i]));
        }
    }


    //    게시판 목록 페이징 처리
    public Page<CommunityDTO> findCommunityPage(Pageable pageable){
        Page<CommunityDTO> communityDTOS = adminCustomRepository.findAllCommunityPage(pageable);

        return communityDTOS;
    }

    //    게시판 목록 조회
    public List<CommunityDTO> findAllCommunity(){
        return  adminCustomRepository.findAllCommunity();
    }

    //    게시판 삭제
    public void deleteByCommunityId(String communityId){
        communityRepository.deleteById(Long.parseLong(communityId));
    }

    //    게시판 체크 여부에 따라 삭제하기
    public void deleteByCommunityCheck(String communityIds){
        String[] arCommunityIds = communityIds.split(",");

        for (int i = 0; i < arCommunityIds.length; i++){
            communityRepository.deleteById(Long.parseLong(arCommunityIds[i]));
        }
    }


    //    고민게시판 목록 페이징 처리
    public Page<CounselingDTO> findCounselingPage(Pageable pageable){
        Page<CounselingDTO> counselingDTOS = adminCustomRepository.findAllCounselingPage(pageable);

        return counselingDTOS;
    }

    //    고민게시판 목록 조회
    public List<CounselingDTO> findAllCounseling(){
        return  adminCustomRepository.findAllCounseling();
    }

    //    고민게시판 삭제
    public void deleteByCounselingId(String counselingId){
        counselingRepository.deleteById(Long.parseLong(counselingId));
    }

    //    고민게시판 체크 여부에 따라 삭제하기
    public void deleteByCounselingCheck(String counselingIds){
        String[] arcounselingIds = counselingIds.split(",");

        for (int i = 0; i < arcounselingIds.length; i++){
            counselingRepository.deleteById(Long.parseLong(arcounselingIds[i]));
        }
    }


    //    자료상점 목록 페이징 처리
    public Page<StoreDTO> findStorePage(Pageable pageable){
        Page<StoreDTO> storeDTOS = adminCustomRepository.findAllStorePage(pageable);

        return storeDTOS;
    }

    //    자료상점 목록 조회
    public List<StoreDTO> findAllStore(){
        return  adminCustomRepository.findAllStore();
    }

    //    자료상점 삭제
    public void deleteByStoreId(String storeId){
        storeRepository.deleteById(Long.parseLong(storeId));
    }

    //    자료상점 체크 여부에 따라 삭제하기
    public void deleteByStoreCheck(String storeIds){
        String[] arStoreIds = storeIds.split(",");

        for (int i = 0; i < arStoreIds.length; i++){
            storeRepository.deleteById(Long.parseLong(arStoreIds[i]));
        }
    }

    //    자료상점 첨부파일 가져오기
    public List<StoreFlieDTO> findStoreFileByStoreId(Long storeId){
        return adminCustomRepository.findByStoreId(storeId);
    }


    //    스터디 댓글 목록 페이징 처리
    public Page<StudyFeedReplyDTO> findStudyRePage(Pageable pageable){
        Page<StudyFeedReplyDTO> studyFeedReplyDTOS = adminCustomRepository.findAllStudyReplyPage(pageable);

        return studyFeedReplyDTOS;
    }

    //    스터디 댓글 목록 조회
    public List<StudyFeedReplyDTO> findAllStudyRe(){
        return  adminCustomRepository.findAllStudyReply();
    }

    //    스터디 댓글 삭제
    public void deleteByStudyReId(String studyFeedReplyId){
        studyFeedReplyRepository.deleteById(Long.parseLong(studyFeedReplyId));
    }

    //    스터디 댓글 체크 여부에 따라 삭제하기
    public void deleteByStudyReCheck(String studyFeedReplyIds){
        String[] arStudyFeedReIds = studyFeedReplyIds.split(",");

        for (int i = 0; i < arStudyFeedReIds.length; i++){
            studyFeedReplyRepository.deleteById(Long.parseLong(arStudyFeedReIds[i]));
        }
    }


    //    게시판 댓글 목록 페이징 처리
    public Page<CommunityReplyDTO> findCommunityRePage(Pageable pageable){
        Page<CommunityReplyDTO> communityReplyDTOS = adminCustomRepository.findAllCommunityReplyPage(pageable);

        return communityReplyDTOS;
    }

    //    게시판 댓글 목록 조회
    public List<CommunityReplyDTO> findAllCommunityRe(){
        return  adminCustomRepository.findAllCommunityReply();
    }

    //    게시판 댓글 삭제
    public void deleteByCommunityReId(String communityReplyId){
        communityReplyRepository.deleteById(Long.parseLong(communityReplyId));
    }

    //    게시판 댓글 체크 여부에 따라 삭제하기
    public void deleteByCommunityReCheck(String communityReplyIds){
        String[] arCommunityReIds = communityReplyIds.split(",");

        for (int i = 0; i < arCommunityReIds.length; i++){
            communityReplyRepository.deleteById(Long.parseLong(arCommunityReIds[i]));
        }
    }


    //    고민 게시판 댓글 목록 페이징 처리
    public Page<CounselingReplyDTO> findCounselingRePage(Pageable pageable){
        Page<CounselingReplyDTO> counselingReplyDTOS = adminCustomRepository.findAllCounselingReplyPage(pageable);

        return counselingReplyDTOS;
    }

    //    고민 게시판 댓글 목록 조회
    public List<CounselingReplyDTO> findAllCounselingRe(){
        return  adminCustomRepository.findAllCounselingReply();
    }

    //    고민 게시판 댓글 삭제
    public void deleteByCounselingReId(String counselingReplyId){
        counselingReplyRepository.deleteById(Long.parseLong(counselingReplyId));
    }

    //    고민 게시판 댓글 체크 여부에 따라 삭제하기
    public void deleteByCounselingReCheck(String counselingReplyIds){
        String[] arCounselingReIds = counselingReplyIds.split(",");

        for (int i = 0; i < arCounselingReIds.length; i++){
            counselingReplyRepository.deleteById(Long.parseLong(arCounselingReIds[i]));
        }
    }


    //    자료 상점 댓글 목록 페이징 처리
    public Page<StoreReplyDTO> findStoreRePage(Pageable pageable){
        Page<StoreReplyDTO> storeReplyDTOS = adminCustomRepository.findAllStoreReplyPage(pageable);

        return storeReplyDTOS;
    }

    //    자료 상점 댓글 목록 조회
    public List<StoreReplyDTO> findAllStoreRe(){
        return  adminCustomRepository.findAllStoreReply();
    }

    //    자료 상점 댓글 삭제
    public void deleteByStoreReId(String storeReplyId){
        storeReplyRepository.deleteById(Long.parseLong(storeReplyId));
    }

    //    자료 상점 댓글 체크 여부에 따라 삭제하기
    public void deleteByStoreReCheck(String storeReplyIds){
        String[] arStoreReIds = storeReplyIds.split(",");

        for (int i = 0; i < arStoreReIds.length; i++){
            storeReplyRepository.deleteById(Long.parseLong(arStoreReIds[i]));
        }
    }


    //    문의하기 목록 페이징 처리
    public Page<InquiryDTO> findInquiryPage(Pageable pageable){
        Page<InquiryDTO> inquiryDTOS = adminCustomRepository.findAllInquiryPage(pageable);

        return inquiryDTOS;
    }

    //    문의하기 목록 조회
    public List<InquiryDTO> findInquiry(){
        return  adminCustomRepository.findAllInquiry();
    }

    //    문의하기 삭제
    public void deleteByInquiryId(String inquiryId){
        inquiryRepository.deleteById(Long.parseLong(inquiryId));
    }

    //    문의하기 체크 여부에 따라 삭제하기
    public void deleteByInquiryCheck(String inquiryIds){
        String[] arInquiryIds = inquiryIds.split(",");

        for (int i = 0; i < arInquiryIds.length; i++){
            inquiryRepository.deleteById(Long.parseLong(arInquiryIds[i]));
        }
    }

    //    문의하기 번호로 조회
    public InquiryDTO findByInquiryId(Long inquiryId){
        return adminCustomRepository.findByInquiryId(inquiryId);
    }


    //    관리자 메인에 뿌려줄 정보들
    public List<UserDTO> findMainUser(){
        return adminCustomRepository.findMainUser();
    }

    public List<StudyDTO> findMainStudy(){
        return adminCustomRepository.findMainStudy();
    }

    public List<InquiryDTO> findMainInquiry(){
        return adminCustomRepository.findMainInquiry();
    }

    public List<CollegeDTO> findMainCollege(){
        return adminCustomRepository.findMainCollege();
    }



}

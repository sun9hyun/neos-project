package com.app.neos.repository.admin;

import com.app.neos.domain.Admin.AdminUserDTO;
import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.college.QCollegeDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.domain.community.QCommunityDTO;
import com.app.neos.domain.community.QCommunityReplyDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.domain.counseling.QCounselingDTO;
import com.app.neos.domain.counseling.QCounselingReplyDTO;
import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.domain.inquiry.QInquiryDTO;
import com.app.neos.domain.store.*;
import com.app.neos.domain.study.QStudyDTO;
import com.app.neos.domain.study.QStudyFeedReplyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.QCollege;
import com.app.neos.entity.community.QCommunity;
import com.app.neos.entity.community.QCommunityReply;
import com.app.neos.entity.counseling.QCounseling;
import com.app.neos.entity.counseling.QCounselingReply;
import com.app.neos.entity.inquiry.QInquiry;
import com.app.neos.entity.store.QStore;
import com.app.neos.entity.store.QStoreFile;
import com.app.neos.entity.store.QStoreReply;
import com.app.neos.entity.study.QStudy;
import com.app.neos.entity.study.QStudyFeedReply;
import com.app.neos.entity.study.QStudyFollow;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.app.neos.repository.study.StudyFollowRepository;
import com.app.neos.service.admin.AdminService;
import com.app.neos.type.inquiry.InquiryStatus;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.List;

import static com.app.neos.entity.college.QCollege.college;
import static com.app.neos.entity.community.QCommunity.community;
import static com.app.neos.entity.community.QCommunityReply.communityReply;
import static com.app.neos.entity.counseling.QCounseling.counseling;
import static com.app.neos.entity.counseling.QCounselingReply.counselingReply;
import static com.app.neos.entity.inquiry.QInquiry.inquiry;
import static com.app.neos.entity.store.QStore.store;
import static com.app.neos.entity.store.QStoreFile.storeFile;
import static com.app.neos.entity.store.QStoreReply.storeReply;
import static com.app.neos.entity.study.QStudy.study;
import static com.app.neos.entity.study.QStudyFeedReply.studyFeedReply;
import static com.app.neos.entity.user.QUser.user;


@Repository
@RequiredArgsConstructor
public class AdminCustomRepositoryImpl implements AdminCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final StudyFollowRepository studyFollowRepository;

    @Override
    public User findByUserId(Long userId) {
        User user = jpaQueryFactory.selectFrom(QUser.user)
                .where(QUser.user.userId.eq(userId))
                .fetchOne();

        return user;
    }

    @Override
    public UserDTO findByUserDTOId(Long userId) {
        UserDTO userDTO = jpaQueryFactory.select(new QUserDTO(
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
                .where(user.userId.eq(userId))
                .fetchOne();

        return userDTO;
    }

    @Override
    public Page<StudyDTO> findAllStudyPage(Pageable pageable) {

        List<StudyDTO> studyDTOS = jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyTitle,
                study.user.userNickName,
                study.createdDate,
                study.followList.size()
                ))
                .from(study)
                .orderBy(study.studyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyTitle,
                study.user.userNickName,
                study.createdDate,
                study.followList.size()
        ))
                .from(study)
                .fetch().size();

        return new PageImpl<>(studyDTOS,pageable,total);
    }

    @Override
    public List<StudyDTO> findAllStudy() {
        List<StudyDTO> studyDTOS = jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyTitle,
                study.user.userNickName,
                study.createdDate,
                study.followList.size()
                ))
                .from(study)
                .orderBy(study.studyId.desc())
                .fetch();

        return studyDTOS;
    }

    @Override
    public Page<CommunityDTO> findAllCommunityPage(Pageable pageable) {

        List<CommunityDTO> communityDTOS = jpaQueryFactory.select(new QCommunityDTO(
                community.communityId,
                community.communityTitle,
                community.communityLikeCount,
                community.user.userNickName,
                community.createdDate
                 ))
                .from(community)
                .orderBy(community.communityId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QCommunityDTO(
                community.communityId,
                community.communityTitle,
                community.communityLikeCount,
                community.user.userNickName,
                community.createdDate
                ))
                .from(community)
                .fetch().size();

        return new PageImpl<>(communityDTOS,pageable,total);
    }

    @Override
    public List<CommunityDTO> findAllCommunity() {
        List<CommunityDTO> communityDTOS = jpaQueryFactory.select(new QCommunityDTO(
                community.communityId,
                community.communityTitle,
                community.communityLikeCount,
                community.user.userNickName,
                community.createdDate
        ))
                .from(community)
                .orderBy(community.communityId.desc())
                .fetch();

        return communityDTOS;
    }

    @Override
    public Page<CounselingDTO> findAllCounselingPage(Pageable pageable) {

        List<CounselingDTO> counselingDTOS = jpaQueryFactory.select(new QCounselingDTO(
                counseling.counselingId,
                counseling.counselingTitle,
                counseling.user.userNickName,
                counseling.createdDate
                ))
                .from(counseling)
                .orderBy(counseling.counselingId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QCounselingDTO(
                counseling.counselingId,
                counseling.counselingTitle,
                counseling.user.userNickName,
                counseling.createdDate
        ))
                .from(counseling)
                .fetch().size();

        return new PageImpl<>(counselingDTOS,pageable,total);
    }

    @Override
    public List<CounselingDTO> findAllCounseling() {

        List<CounselingDTO> counselingDTOS = jpaQueryFactory.select(new QCounselingDTO(
                counseling.counselingId,
                counseling.counselingTitle,
                counseling.user.userNickName,
                counseling.createdDate
                ))
                .from(counseling)
                .orderBy(counseling.counselingId.desc())
                .fetch();

        return counselingDTOS;
    }

    @Override
    public Page<StoreDTO> findAllStorePage(Pageable pageable) {
        List<StoreDTO> storeDTOS = jpaQueryFactory.select(new QStoreDTO(
                store.storeId,
                store.storeTitle,
                store.user.userNickName,
                store.createdDate
                ))
                .from(store)
                .orderBy(store.storeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QStoreDTO(
                store.storeId,
                store.storeTitle,
                store.user.userNickName,
                store.createdDate
        ))
                .from(store)
                .fetch().size();

        return new PageImpl<>(storeDTOS,pageable,total);
    }

    @Override
    public List<StoreDTO> findAllStore() {
        List<StoreDTO> storeDTOS = jpaQueryFactory.select(new QStoreDTO(
                store.storeId,
                store.storeTitle,
                store.user.userNickName,
                store.createdDate
        ))
                .from(store)
                .fetch();
        return storeDTOS;
    }

    @Override
    public List<StoreFlieDTO> findByStoreId(Long storeId) {
            return jpaQueryFactory.select(new QStoreFlieDTO(
                    storeFile.storeFileId,
                    storeFile.storeFileName,
                    storeFile.storeFileQR,
                    storeFile.store.storeId
            ))
                    .from(storeFile)
                    .where(storeFile.store.storeId.eq(storeId))
                    .fetch();
    }

    @Override
    public Page<StudyFeedReplyDTO> findAllStudyReplyPage(Pageable pageable) {
        List<StudyFeedReplyDTO> storeDTOS = jpaQueryFactory.select(new QStudyFeedReplyDTO(
                studyFeedReply.studyFeedReplyId,
                studyFeedReply.studyFeedReplyContent,
                studyFeedReply.studyFeedReplyWriter.userNickName,
                studyFeedReply.createdDate,
                studyFeedReply.studyFeed.study.studyTitle
                ))
                .from(studyFeedReply)
                .orderBy(studyFeedReply.studyFeedReplyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QStudyFeedReplyDTO(
                studyFeedReply.studyFeedReplyId,
                studyFeedReply.studyFeedReplyContent,
                studyFeedReply.studyFeedReplyWriter.userNickName,
                studyFeedReply.createdDate,
                studyFeedReply.studyFeed.study.studyTitle
                ))
                .from(studyFeedReply)
                .fetch().size();

        return new PageImpl<>(storeDTOS,pageable,total);
    }

    @Override
    public List<StudyFeedReplyDTO> findAllStudyReply() {
        List<StudyFeedReplyDTO> studyFeedReplyDTOS = jpaQueryFactory.select(new QStudyFeedReplyDTO(
                studyFeedReply.studyFeedReplyId,
                studyFeedReply.studyFeedReplyContent,
                studyFeedReply.studyFeedReplyWriter.userNickName,
                studyFeedReply.createdDate,
                studyFeedReply.studyFeed.study.studyTitle
                ))
                .from(studyFeedReply)
                .fetch();

        return studyFeedReplyDTOS;
    }

    @Override
    public Page<CommunityReplyDTO> findAllCommunityReplyPage(Pageable pageable) {
        List<CommunityReplyDTO> communityReplyDTOS = jpaQueryFactory.select(new QCommunityReplyDTO(
                communityReply.communityReplyId,
                communityReply.communityReplyContent,
                communityReply.user.userNickName,
                communityReply.community.communityTitle,
                communityReply.createdDate
                ))
                .from(communityReply)
                .orderBy(communityReply.communityReplyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QCommunityReplyDTO(
                communityReply.communityReplyId,
                communityReply.communityReplyContent,
                communityReply.user.userNickName,
                communityReply.community.communityTitle,
                communityReply.createdDate
        ))
                .from(communityReply)
                .fetch().size();

        return new PageImpl<>(communityReplyDTOS,pageable,total);
    }

    @Override
    public List<CommunityReplyDTO> findAllCommunityReply() {
        List<CommunityReplyDTO> communityReplyDTOS = jpaQueryFactory.select(new QCommunityReplyDTO(
                communityReply.communityReplyId,
                communityReply.communityReplyContent,
                communityReply.user.userNickName,
                communityReply.community.communityTitle,
                communityReply.createdDate
        ))
                .from(communityReply)
                .fetch();
        return communityReplyDTOS;
    }

    @Override
    public Page<CounselingReplyDTO> findAllCounselingReplyPage(Pageable pageable) {
        List<CounselingReplyDTO> counselingReplyDTOS = jpaQueryFactory.select(new QCounselingReplyDTO(
                counselingReply.counselingReplyId,
                counselingReply.counselingReplyContent,
                counselingReply.user.userNickName,
                counselingReply.counseling.counselingTitle,
                counselingReply.createdDate
                ))
                .from(counselingReply)
                .orderBy(counselingReply.counselingReplyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QCounselingReplyDTO(
                counselingReply.counselingReplyId,
                counselingReply.counselingReplyContent,
                counselingReply.user.userNickName,
                counselingReply.counseling.counselingTitle,
                counselingReply.createdDate
        ))
                .from(counselingReply)
                .fetch().size();

        return new PageImpl<>(counselingReplyDTOS,pageable,total);

    }

    @Override
    public List<CounselingReplyDTO> findAllCounselingReply() {
        List<CounselingReplyDTO> counselingReplyDTOS = jpaQueryFactory.select(new QCounselingReplyDTO(
                counselingReply.counselingReplyId,
                counselingReply.counselingReplyContent,
                counselingReply.user.userNickName,
                counselingReply.counseling.counselingTitle,
                counselingReply.createdDate
        ))
                .from(counselingReply)
                .fetch();

        return counselingReplyDTOS;
    }

    @Override
    public Page<StoreReplyDTO> findAllStoreReplyPage(Pageable pageable) {
        List<StoreReplyDTO> storeReplyDTOS = jpaQueryFactory.select(new QStoreReplyDTO(
                storeReply.storeReplyId,
                storeReply.storeReplyContent,
                storeReply.user.userNickName,
                storeReply.store.storeTitle,
                storeReply.createdDate
        ))
                .from(storeReply)
                .orderBy(storeReply.storeReplyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QStoreReplyDTO(
                storeReply.storeReplyId,
                storeReply.storeReplyContent,
                storeReply.user.userNickName,
                storeReply.store.storeTitle,
                storeReply.createdDate
        ))
                .from(storeReply)
                .fetch().size();

        return new PageImpl<>(storeReplyDTOS,pageable,total);
    }

    @Override
    public List<StoreReplyDTO> findAllStoreReply() {

        List<StoreReplyDTO> storeReplyDTOS = jpaQueryFactory.select(new QStoreReplyDTO(
                storeReply.storeReplyId,
                storeReply.storeReplyContent,
                storeReply.user.userNickName,
                storeReply.store.storeTitle,
                storeReply.createdDate
        ))
                .from(storeReply)
                .fetch();
        return storeReplyDTOS;
    }


    @Override
    public Page<InquiryDTO> findAllInquiryPage(Pageable pageable) {
        List<InquiryDTO> inquiryDTOS = jpaQueryFactory.select(new QInquiryDTO(
                inquiry.inquiryId,
                inquiry.inquiryContent,
                inquiry.inquiryReply,
                inquiry.inquiryStatus,
                inquiry.user.userNickName,
                inquiry.createdDate
        ))
                .from(inquiry)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(inquiry.inquiryStatus.desc())
                .fetch();

        long total = jpaQueryFactory.select(new QInquiryDTO(
                inquiry.inquiryId,
                inquiry.inquiryContent,
                inquiry.inquiryReply,
                inquiry.inquiryStatus,
                inquiry.user.userNickName,
                inquiry.createdDate
        ))
                .from(inquiry)
                .fetch().size();

        return new PageImpl<>(inquiryDTOS,pageable,total);
    }

    @Override
    public List<InquiryDTO> findAllInquiry() {
        List<InquiryDTO> inquiryDTOS = jpaQueryFactory.select(new QInquiryDTO(
                inquiry.inquiryId,
                inquiry.inquiryContent,
                inquiry.inquiryReply,
                inquiry.inquiryStatus,
                inquiry.user.userNickName,
                inquiry.createdDate
        ))
                .from(inquiry)
                .fetch();

        return  inquiryDTOS;
    }

    @Override
    public InquiryDTO findByInquiryId(Long inquiryId) {
        InquiryDTO inquiryDTO = jpaQueryFactory.select(new QInquiryDTO(
                inquiry.inquiryId,
                inquiry.inquiryContent,
                inquiry.inquiryReply,
                inquiry.inquiryStatus,
                inquiry.user.userNickName,
                inquiry.createdDate
        ))
                .from(inquiry)
                .where(inquiry.inquiryId.eq(inquiryId))
                .fetchOne();

        return inquiryDTO;
    }



    @Override
    public List<UserDTO> findMainUser() {
        List<UserDTO> userDTOS = jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userNickName,
                user.user.userFile,
                user.userOAuthId,
                user.createdDate
        ))
                .from(user).orderBy(user.createdDate.desc())
                .limit(6).fetch();

        return userDTOS;
    }

    @Override
    public List<StudyDTO> findMainStudy() {
        List<StudyDTO> studyDTOS = jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyTitle,
                study.user.userNickName,
                study.user.userFile,
                study.createdDate,
                study.followList.size()
        ))
                .from(study).orderBy(study.createdDate.desc())
                .limit(6).fetch();

        return studyDTOS;
    }

    @Override
    public List<InquiryDTO> findMainInquiry() {
        List<InquiryDTO> inquiryDTOS = jpaQueryFactory.select(new QInquiryDTO(
                inquiry.inquiryId,
                inquiry.inquiryContent,
                inquiry.inquiryReply,
                inquiry.inquiryStatus,
                inquiry.user.userNickName,
                inquiry.user.userFile,
                inquiry.createdDate
        ))
                .from(inquiry).orderBy(inquiry.createdDate.desc())
                .where(inquiry.inquiryStatus.eq(InquiryStatus.WAITING))
                .limit(3).fetch();

        return inquiryDTOS;
    }

    @Override
    public List<CollegeDTO> findMainCollege() {
        List<CollegeDTO> collegeDTOS = jpaQueryFactory.select(new QCollegeDTO(
                college.collegeId,
                college.collegeCity,
                college.collegeName,
                college.collegeLogoFile,
                college.collegeEmailDomain,
                college.createdDate,
                college.updatedDate
        ))
                .from(college).orderBy(college.createdDate.desc())
                .limit(3)
                .fetch();

        return collegeDTOS;
    }

//    @Override
//    public Page<UserDTO> findAllUserPage(Pageable pageable) {
//
//
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
//                ))
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
//                ))
//                .from(user)
//                .fetch().size();
//
//        return new PageImpl<>(userDTOS,pageable,total);
//
//    }


}

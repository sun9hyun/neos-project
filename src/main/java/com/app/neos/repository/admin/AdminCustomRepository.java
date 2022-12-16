package com.app.neos.repository.admin;

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
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.StudyFeedReply;
import com.app.neos.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCustomRepository {
//    PK 로 유저 엔티티 찾기
    public User findByUserId(Long userId);

//    PK 로 유저 DTO 찾기
    public UserDTO findByUserDTOId(Long userId);


//    스터디 목록 페이징 처리
    public Page<StudyDTO> findAllStudyPage(Pageable pageable);

//    모든 스터디 정보 스터디 DTO 로 가져오기
    public List<StudyDTO> findAllStudy();

//    자유 게시판 목록 페이징 처리
    public Page<CommunityDTO> findAllCommunityPage(Pageable pageable);

//    모든 자유 게시판 정보 자유 게시판 DTO 로 가져오기
    public List<CommunityDTO> findAllCommunity();

//    고민 상담 게시판 목록 페이징 처리
    public Page<CounselingDTO> findAllCounselingPage(Pageable pageable);

//    모든 고민 상담 게시판 정보 고민 상담 DTO 로 가져오기
    public List<CounselingDTO> findAllCounseling();

//    자료 상점 목록 페이징 처리
    public Page<StoreDTO> findAllStorePage(Pageable pageable);

//    모든 자료 상점 정보 자료 상점 DTO 로 가져오기
    public List<StoreDTO> findAllStore();

//    PK 로 해당 자료 상점의 첨부파일 DTO 로 가져오기
    public List<StoreFlieDTO> findByStoreId(Long storeId);



//    스터디 댓글 목록 페이징 처리
    public Page<StudyFeedReplyDTO> findAllStudyReplyPage(Pageable pageable);

//    모든 스터디 댓글 DTO 로 가져오기
    public List<StudyFeedReplyDTO> findAllStudyReply();

//    자유 게시판 댓글 목록 페이징 처리
    public Page<CommunityReplyDTO> findAllCommunityReplyPage(Pageable pageable);

//    모든 사유 게시판 댓글 DTO 로 가져오기
    public List<CommunityReplyDTO> findAllCommunityReply();

//    고민 상담 댓글 목록 페이징 처리
    public Page<CounselingReplyDTO> findAllCounselingReplyPage(Pageable pageable);

//    모든 고민 상담 게시판 댓글 DTO 로 가져오기
    public List<CounselingReplyDTO> findAllCounselingReply();

//    자료 상점 댓글 목록 페이징 처리
    public Page<StoreReplyDTO> findAllStoreReplyPage(Pageable pageable);

//    모든 자료 상점 게시판 댓글 DTO 로 가져오기
    public List<StoreReplyDTO> findAllStoreReply();

//    문의하기 목록 페이징 처리
    public Page<InquiryDTO> findAllInquiryPage(Pageable pageable);

//    모든 문의 하기 DTO 로 가져오기
    public List<InquiryDTO> findAllInquiry();

//    PK 로 문의하기 DTO 로 가져오기
    public InquiryDTO findByInquiryId(Long inquiryId);


//    관리자 메인으로 뿌려 줄 유저 DTO 로 뿌려주기
    public List<UserDTO> findMainUser();

//    관리자 메인으로 뿌려 줄 스터디 DTO 로 뿌려주기
    public List<StudyDTO> findMainStudy();

//    관리자 메인으로 뿌려 줄 문의 DTO 로 뿌려주기
    public List<InquiryDTO> findMainInquiry();

//    관리자 메인으로 뿌려 줄 대학교 DTO 로 뿌려주기
    public List<CollegeDTO> findMainCollege();


}

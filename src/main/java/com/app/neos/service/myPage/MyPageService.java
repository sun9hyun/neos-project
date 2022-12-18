package com.app.neos.service.myPage;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.study.StudyFollow;
import com.app.neos.entity.study.StudySupporter;
import com.app.neos.entity.user.User;
import com.app.neos.repository.college.CollegeRepository;
import com.app.neos.repository.myPage.*;
import com.app.neos.repository.store.StoreCustomRepository;
import com.app.neos.repository.store.StoreReplyCustomRepository;
import com.app.neos.repository.store.StoreReplyRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.study.StudyFollowCustomRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;
    private final StoreCustomRepository storeCustomRepository;
    private final StudyCustomRepository studyCustomRepository;
    private final CollegeRepository collegeRepository;
    private final MyPageNeosPowerCustomRepository myPageNeosPowerCustomRepository;
    private final MypageNeosPowerRepository myPageNeosPowerRepository;
    private final MyPageNeosPointCustomRepository myPageNeosPointCustomRepository;
    private final MyPageStudyFollowCustomRepository myPageStudyFollowCustomRepository;
    private final MyPageStudyCustomRepository myPageStudyCustomRepository;
    private final MyPageMemberCustomRepository myPageMemberCustomRepository;
    private final MyPageFollowCustomRepository myPageFollowCustomRepository;
    private final MyPageSupporterCustomRepository myPageSupporterCustomRepository;
    private final MyPageUserCustomRepository myPageUserCustomRepository;
    private final MyPageCollegeCustomRepository myPageCollegeCustomRepository;

    // 마이페이지 유저 업데이트
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDTO userDTO){
        System.out.println("*********************서비스 " + userDTO.getUserCollegeCertify() + "*********************");

        updateEntity(userDTO);
        User user = userRepository.findById(userDTO.getUserId()).get();

        String collegeName = userDTO.getCollegeName();
        CollegeDTO collegeDTO = myPageCollegeCustomRepository.findCollege(collegeName);
        College college = collegeRepository.findById(collegeDTO.getCollegeId()).get();
        System.out.println("*********************서비스college " + college+ "*********************");
        user.changeCollege(college);

        myPageUserCustomRepository.update(user);
    }

    @Transactional
    public void updateEntity(UserDTO userDTO){
        User user = userRepository.findById(userDTO.getUserId()).get();
        user.update(userDTO);
    }

    // 마이페이지 대학 아이디 찾기
    public CollegeDTO findCollege(String college) {
        return myPageCollegeCustomRepository.findCollege(college);
    }
    
    // 마이페이지 스터디 구독 조회
    public List<StudyDTO> findStudyFollow(Long userId) {
        List<StudyFollow> studyFollows = myPageStudyFollowCustomRepository.findByUserId(userId);

        List<StudyDTO> studyDTOS = new ArrayList<>();

        for(StudyFollow studyFollow:studyFollows){
            Long studyId = studyFollow.getStudy().getStudyId();
            StudyDTO studyDTO = myPageStudyCustomRepository.findStudyDTO(studyId);

            studyDTOS.add(studyDTO);
        }
        return studyDTOS;
    }

    // 마이페이지 네오스인 구독 조회
    public List<UserDTO> findFollow(Long userId) {
        List<Follow> follows = myPageFollowCustomRepository.findByUserId(userId);

        List<UserDTO> userDTOS = new ArrayList<>();

        for(Follow follow:follows){
            Long followingId = follow.getFollowingId().getUserId();
            UserDTO userDTO = userRepository.findById(followingId).get().toDTO();

            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    // 마이페이지 참여한 스터디 조회
    public List<StudyDTO> findJoinStudy(Long userId){
        List<StudyMemberDTO> studyMemberDTOS = myPageMemberCustomRepository.findByUserId(userId);

        List<StudyDTO> studyDTOS = new ArrayList<>();

        for(StudyMemberDTO studyMemberDTO:studyMemberDTOS){
            Long studyId = studyMemberDTO.getStudyId();
            StudyDTO studyDTO = myPageStudyCustomRepository.findStudyDTO(studyId);

            studyDTOS.add(studyDTO);
        }
        return studyDTOS;
    }

    // 마이페이지 지원한 스터디 조회
    public List<StudyDTO> findSupportStudy(Long userId){
        List<StudySupporterDTO> studySupporterDTOS = myPageSupporterCustomRepository.findByUserId(userId);

        List<StudyDTO> studyDTOS = new ArrayList<>();

        for(StudySupporterDTO studySupporterDTO:studySupporterDTOS){
            if (studySupporterDTO.getStudySupporterStatus().toString() == "WAIT"){
                Long studyId = studySupporterDTO.getStudyId();
                StudyDTO studyDTO = myPageStudyCustomRepository.findStudyDTO(studyId);

                studyDTOS.add(studyDTO);
            }
        }
        return studyDTOS;
    }

    // 마이페이지 네오력 조회
    public List<NeosPowerDTO> findNeosPower(Long userId) {
        return myPageNeosPowerCustomRepository.neosPowerList(userId);
    }

    // 마이페이지 네오포인트 조회
    public List<NeosPointDTO> findNeosPoint(Long userId){
        return myPageNeosPointCustomRepository.neosPointList(userId);
    }
    
//    // 마이페이지 유저 정보 전달(대학교제외)
//    public User findUserById(Long userId){
//        return userRepository.findById(userId).get();
//    }
//
//    // 마이페이지 유저 정보 전달
//    public UserDTO findUserById2(Long userId){
//        return userCustomRepository.findById(userId);
//    }

}

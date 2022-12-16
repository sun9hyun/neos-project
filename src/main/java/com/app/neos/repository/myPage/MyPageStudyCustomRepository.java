package com.app.neos.repository.myPage;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.study.StudyFollow;

import java.util.List;

public interface MyPageStudyCustomRepository {

    // 마이페이지 구독한 스터디 글 조회
    public List<StudyFollow> findStudyFollow(Long userId);

    // 마이페이지 스터디ID로 StudyDTO 조회
    public StudyDTO findStudyDTO(Long studyId);

    // 마이페이지 유저ID로 참여중인 Study조회
    public List<StudyDTO> findMemberStudyByUserId(Long userId);

    // 마이페이지 스터디 관련 글 조회
    public List<StudyDTO> findStudyByUserId(Long userId);
}

package com.app.neos.repository.myPage;

import com.app.neos.domain.study.StudyDTO;

import java.util.List;

public interface MyPageStudyCustomRepository {

    // 마이페이지 스터디 관련 글 조회
    public List<StudyDTO> findStudyByUserId(Long userID);
}

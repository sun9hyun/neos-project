package com.app.neos.repository.myPage;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.study.StudyFollow;

import java.util.List;

public interface MyPageStudyFollowCustomRepository {

    public List<StudyFollow> findByUserId(Long userId);

}

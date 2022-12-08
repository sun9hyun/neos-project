package com.app.neos.repository.study;

import com.app.neos.entity.study.StudyFollow;

public interface StudyFollowCustomRepository {

    public boolean duplicate(Long userId, Long studyId);

    public StudyFollow findByIdAndStudyId(Long userId, Long studyId);
}

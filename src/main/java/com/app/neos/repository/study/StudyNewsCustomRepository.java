package com.app.neos.repository.study;

import com.app.neos.entity.study.StudyNews;

import java.util.Optional;

public interface StudyNewsCustomRepository {

    public StudyNews findByStudyIdAndMileStoneId(Long studyId, Long mileStoneId);
    public StudyNews findByStudyIdAndWorkId(Long studyId, Long workId);
    public StudyNews findByStudyIdAndMemberId(Long studyId, Long memberId);
}

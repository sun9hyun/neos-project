package com.app.neos.repository.study;

import com.app.neos.entity.study.StudyMilestone;

import java.util.List;

public interface StudyMileStoneCustomRepository {

    public List<StudyMilestone> findAllMileStone(Long studyId);
    public List<StudyMilestone> findAllMileStoneFinish(Long studyId);
}

package com.app.neos.repository.study;

import com.app.neos.entity.study.StudySupporter;

import java.util.List;

public interface StudySupporterCustomRepository {

    public List<StudySupporter> findAllWait(Long studyId);
}

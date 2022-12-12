package com.app.neos.repository.study;

import com.app.neos.entity.study.StudyWork;

import java.util.List;

public interface StudyWorkCustomRepository {

    public List<StudyWork> findAllProceeding(Long studyId);

    public List<StudyWork> findAllFinish(Long studyId);
}

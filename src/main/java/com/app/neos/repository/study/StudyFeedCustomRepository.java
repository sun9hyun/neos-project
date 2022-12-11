package com.app.neos.repository.study;

import com.app.neos.entity.study.StudyFeed;

import java.util.List;

public interface StudyFeedCustomRepository {

    public List<StudyFeed> findAllRecent(Long studyId);
}

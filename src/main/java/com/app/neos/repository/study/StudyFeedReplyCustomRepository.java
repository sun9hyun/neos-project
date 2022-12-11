package com.app.neos.repository.study;

import com.app.neos.entity.study.StudyFeedReply;

import java.util.List;

public interface StudyFeedReplyCustomRepository {

    public List<StudyFeedReply> findAllFeed(Long feedId);
}

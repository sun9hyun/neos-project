package com.app.neos.repository.study;

import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.entity.study.StudyQuestionReply;

import java.util.List;

public interface StudyQuestionReplyCustomRepository {
    public List<StudyQuestionReply> findAllByStudyQuestionId(Long studyQuestionId);

}

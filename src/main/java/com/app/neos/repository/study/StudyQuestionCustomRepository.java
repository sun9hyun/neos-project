package com.app.neos.repository.study;

import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.entity.study.StudyQuestion;

import java.util.List;
import java.util.Optional;

public interface StudyQuestionCustomRepository {
    public List<StudyQuestion> findByStudyId(Long studyId);

}

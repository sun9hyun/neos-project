package com.app.neos.domain.study;

import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyQuestion;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StudyQuestionDTO {
    private Long studyQuestionId;
    private String studyQuestionContent;
    private User studyQuestionWriter;
    private Study study;

    public StudyQuestion toEntity(){
        return StudyQuestion.builder()
                .studyQuestionContent(studyQuestionContent)
                .build();
    }

    @QueryProjection
    public StudyQuestionDTO(Long studyQuestionId, String studyQuestionContent, User studyQuestionWriter, Study study) {
        this.studyQuestionId = studyQuestionId;
        this.studyQuestionContent = studyQuestionContent;
        this.studyQuestionWriter = studyQuestionWriter;
        this.study = study;
    }
}

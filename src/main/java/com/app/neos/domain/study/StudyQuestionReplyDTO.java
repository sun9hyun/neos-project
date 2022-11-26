package com.app.neos.domain.study;

import com.app.neos.entity.study.StudyQuestion;
import com.app.neos.entity.study.StudyQuestionReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StudyQuestionReplyDTO {
    private Long studyQuestionReplyId;
    private String studyQuestionReplyContent;
    private User studyQuestionReplyWriter;
    private StudyQuestion studyQuestion;

    public StudyQuestionReply toEntity(){
        return StudyQuestionReply.builder()
                .studyQuestionReplyContent(studyQuestionReplyContent)
                .build();
    }

    @QueryProjection
    public StudyQuestionReplyDTO(Long studyQuestionReplyId, String studyQuestionReplyContent, User studyQuestionReplyWriter, StudyQuestion studyQuestion) {
        this.studyQuestionReplyId = studyQuestionReplyId;
        this.studyQuestionReplyContent = studyQuestionReplyContent;
        this.studyQuestionReplyWriter = studyQuestionReplyWriter;
        this.studyQuestion = studyQuestion;
    }
}

package com.app.neos.domain.study;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.StudyQuestion;
import com.app.neos.entity.study.StudyQuestionReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StudyQuestionReplyDTO {
    private Long studyQuestionReplyId;
    private String studyQuestionReplyContent;
    private UserDTO studyQuestionReplyWriter;
    private StudyQuestionDTO studyQuestion;
    private String createdDate;

    public StudyQuestionReply toEntity(){
        return StudyQuestionReply.builder()
                .studyQuestionReplyContent(studyQuestionReplyContent)
                .build();
    }

    @QueryProjection
    public StudyQuestionReplyDTO(Long studyQuestionReplyId, String studyQuestionReplyContent, UserDTO studyQuestionReplyWriter, StudyQuestionDTO studyQuestion, String createdDate) {
        this.studyQuestionReplyId = studyQuestionReplyId;
        this.studyQuestionReplyContent = studyQuestionReplyContent;
        this.studyQuestionReplyWriter = studyQuestionReplyWriter;
        this.studyQuestion = studyQuestion;
        this.createdDate = createdDate;
    }
}

package com.app.neos.domain.study;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyQuestion;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
public class StudyQuestionDTO {
    private Long studyQuestionId;
    private String studyQuestionContent;
    private UserDTO studyQuestionWriter;
    private StudyDTO studyDTO;
    private String createdDate;
    private int replyLength;

    public StudyQuestion toEntity(){
        return StudyQuestion.builder()
                .studyQuestionContent(studyQuestionContent)
                .build();
    }

    @QueryProjection
    public StudyQuestionDTO(Long studyQuestionId, String studyQuestionContent, UserDTO studyQuestionWriter, StudyDTO studyDTO, String createdDate, int replyLength) {
        this.studyQuestionId = studyQuestionId;
        this.studyQuestionContent = studyQuestionContent;
        this.studyQuestionWriter = studyQuestionWriter;
        this.studyDTO = studyDTO;
        this.createdDate = createdDate;
        this.replyLength = replyLength;
    }
}

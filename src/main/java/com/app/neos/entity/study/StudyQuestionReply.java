package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "TBL_STUDY_QUESTION_REPLY")
@Getter @ToString(exclude = {"studyQuestionReplyWriter", "studyQuestion"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyQuestionReply extends Period {
    @Id @GeneratedValue
    private Long studyQuestionReplyId;
    @NotNull
    private String studyQuestionReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User studyQuestionReplyWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StudyQuestion studyQuestion;


    public void changeStudyQuestionReplyWriter(User studyQuestionReplyWriter){
        this.studyQuestionReplyWriter = studyQuestionReplyWriter;
    }

    public void changeStudyQuestion(StudyQuestion studyQuestion){
        this.studyQuestion = studyQuestion;
        studyQuestion.getReplies().add(this);
    }

    @Builder
    public StudyQuestionReply(@NotNull String studyQuestionReplyContent) {
        this.studyQuestionReplyContent = studyQuestionReplyContent;
    }

    public void update(StudyQuestionReplyDTO studyQuestionReplyDTO){
        this.studyQuestionReplyContent = studyQuestionReplyDTO.getStudyQuestionReplyContent();
    }

    public StudyQuestionReplyDTO toDTO(){
        String dateFormat = this.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        StudyQuestionReplyDTO dto = new StudyQuestionReplyDTO();
        dto.setStudyQuestionReplyContent(this.studyQuestionReplyContent);
        dto.setStudyQuestionReplyId(this.studyQuestionReplyId);
        dto.setStudyQuestion(this.studyQuestion.toDTO());
        dto.setStudyQuestionReplyWriter(this.studyQuestionReplyWriter.toDTO());
        dto.setCreatedDate(dateFormat);
        return dto;
    }
}

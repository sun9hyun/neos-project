package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_QUESTION_REPLY")
@Getter @ToString(exclude = {"studyQuestionReplyWriter", "studyQuestion"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyQuestionReply extends Period {
    @Id @GeneratedValue @NonNull
    private Long studyQuestionReplyId;
    @NonNull
    private String studyQuestionReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User studyQuestionReplyWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private StudyQuestion studyQuestion;


    public void changeStudyQuestionReplyWriter(User studyQuestionReplyWriter){
        this.studyQuestionReplyWriter = studyQuestionReplyWriter;
    }

    public void changeStudyQuestion(StudyQuestion studyQuestion){
        this.studyQuestion = studyQuestion;
    }

    @Builder
    public StudyQuestionReply(@NonNull String studyQuestionReplyContent) {
        this.studyQuestionReplyContent = studyQuestionReplyContent;
    }

    public void update(StudyQuestionReplyDTO studyQuestionReplyDTO){
        this.studyQuestionReplyContent = studyQuestionReplyDTO.getStudyQuestionReplyContent();
    }
}

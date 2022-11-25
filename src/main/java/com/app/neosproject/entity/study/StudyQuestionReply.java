package com.app.neosproject.entity.study;

import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_QUESTION_REPLY")
@Getter @ToString(exclude = {"studyQuestionReplyWriter", "studyQuestion"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyQuestionReply extends Period {
    @Id @GeneratedValue
    private Long studyQuestionReplyId;

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
    public StudyQuestionReply(String studyQuestionReplyContent, User studyQuestionReplyWriter, StudyQuestion studyQuestion) {
        this.studyQuestionReplyContent = studyQuestionReplyContent;
        this.studyQuestionReplyWriter = studyQuestionReplyWriter;
        this.studyQuestion = studyQuestion;
    }
}

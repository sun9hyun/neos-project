package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_QUESTION")
@Getter @ToString(exclude = {"studyQuestionWriter", "study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyQuestion extends Period {
    @Id @GeneratedValue
    private Long studyQuestionId;
    @NonNull
    private String studyQuestionContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User studyQuestionWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_ID")
    private Study study;

    public void changeStudyQuestionWriter(User studyQuestionWriter){
        this.studyQuestionWriter = studyQuestionWriter;
    }

    public void changeStudyId(Study study){
        this.study = study;
    }

    @Builder
    public StudyQuestion(@NonNull String studyQuestionContent) {
        this.studyQuestionContent = studyQuestionContent;
    }

    public void update(StudyQuestionDTO studyQuestionDTO){
        this.studyQuestionContent = studyQuestionDTO.getStudyQuestionContent();
    }
}

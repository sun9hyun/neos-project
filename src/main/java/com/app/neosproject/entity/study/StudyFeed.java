package com.app.neosproject.entity.study;

import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_FEED")
@Getter @ToString(exclude = {"studyFeedWriter","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyFeed extends Period {
    @Id @GeneratedValue
    private Long studyFeedId;
    private String studyFeedContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User studyFeedWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_ID")
    private Study study;


    public void changeStudyFeedWriter(User studyFeedWriter){
        this.studyFeedWriter=studyFeedWriter;
    }

    public void changeStudy(Study study){
        this.study=study;
    }

    @Builder
    public StudyFeed(String studyFeedContent, User studyFeedWriter, Study study) {
        this.studyFeedContent = studyFeedContent;
        this.studyFeedWriter = studyFeedWriter;
        this.study = study;
    }
}

package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyFeedDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_FEED")
@Getter @ToString(exclude = {"studyFeedWriter","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyFeed extends Period {
    @Id @GeneratedValue @NonNull
    private Long studyFeedId;
    @NonNull
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
    public StudyFeed(@NonNull String studyFeedContent) {
        this.studyFeedContent = studyFeedContent;
    }

    public void update(StudyFeedDTO studyFeedDTO){
        this.studyFeedContent = studyFeedDTO.getStudyFeedContent();
    }
}

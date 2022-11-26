package com.app.neos.entity.study;


import com.app.neos.domain.study.StudyMilestoneDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.milestone.StudyMilestoneStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_MILESTONE")
@Getter @ToString(exclude = {"mileStoneWriter","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyMilestone extends Period {
    @Id @GeneratedValue @NonNull
    private Long studyMileStoneId;

    @NonNull
    private String studyMileStoneTitle;
    @NonNull
    private String studyMileStoneContent;

    @Enumerated(EnumType.STRING) @NonNull
    private StudyMilestoneStatus studyMilestoneStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User mileStoneWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_ID")
    private Study study;

    public void changeMileStoneWriter(User mileStoneWriter){
        this.mileStoneWriter = mileStoneWriter;
    }

    public void changeStudy(Study study){
        this.study = study;
    }

    @Builder
    public StudyMilestone(@NonNull String studyMileStoneTitle, @NonNull String studyMileStoneContent, @NonNull StudyMilestoneStatus studyMilestoneStatus) {
        this.studyMileStoneTitle = studyMileStoneTitle;
        this.studyMileStoneContent = studyMileStoneContent;
        this.studyMilestoneStatus = studyMilestoneStatus;
    }

    public void update(StudyMilestoneDTO studyMilestoneDTO){
        this.studyMileStoneTitle = studyMilestoneDTO.getStudyMileStoneTitle();
        this.studyMileStoneContent = studyMilestoneDTO.getStudyMileStoneContent();
        this.studyMilestoneStatus = studyMilestoneDTO.getStudyMilestoneStatus();
    }

}

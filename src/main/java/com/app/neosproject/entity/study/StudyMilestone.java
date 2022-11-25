package com.app.neosproject.entity.study;


import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import com.app.neosproject.type.study.milestone.StudyMilestoneStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_MILESTONE")
@Getter @ToString(exclude = {"mileStoneWriter","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyMilestone extends Period {
    @Id @GeneratedValue
    private Long studyMileStoneId;

    private String studyMileStoneTitle;
    private String studyMileStoneContent;

    @Enumerated(EnumType.STRING)
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

    public StudyMilestone(String studyMileStoneTitle, String studyMileStoneContent, StudyMilestoneStatus studyMilestoneStatus, User mileStoneWriter, Study study) {
        this.studyMileStoneTitle = studyMileStoneTitle;
        this.studyMileStoneContent = studyMileStoneContent;
        this.studyMilestoneStatus = studyMilestoneStatus;
        this.mileStoneWriter = mileStoneWriter;
        this.study = study;
    }
}

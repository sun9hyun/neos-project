package com.app.neos.domain.study;


import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyMilestone;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.milestone.StudyMilestoneStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StudyMilestoneDTO {
    private Long studyMileStoneId;
    private String studyMileStoneTitle;
    private String studyMileStoneContent;
    private StudyMilestoneStatus studyMilestoneStatus;
    private User mileStoneWriter;
    private Study study;

    public StudyMilestone toEntity(){
        return StudyMilestone.builder()
                .studyMileStoneTitle(studyMileStoneTitle)
                .studyMileStoneContent(studyMileStoneContent)
                .studyMilestoneStatus(studyMilestoneStatus)
                .build();
    }

    @QueryProjection
    public StudyMilestoneDTO(Long studyMileStoneId, String studyMileStoneTitle, String studyMileStoneContent, StudyMilestoneStatus studyMilestoneStatus, User mileStoneWriter, Study study) {
        this.studyMileStoneId = studyMileStoneId;
        this.studyMileStoneTitle = studyMileStoneTitle;
        this.studyMileStoneContent = studyMileStoneContent;
        this.studyMilestoneStatus = studyMilestoneStatus;
        this.mileStoneWriter = mileStoneWriter;
        this.study = study;
    }
}

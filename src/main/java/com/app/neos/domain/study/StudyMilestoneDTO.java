package com.app.neos.domain.study;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.StudyMilestone;
import com.app.neos.type.study.milestone.StudyMilestoneStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class StudyMilestoneDTO {
    private Long studyMileStoneId;
    private String studyMileStoneTitle;
        private String studyMileStoneContent;
    private StudyMilestoneStatus studyMilestoneStatus;
    private UserDTO mileStoneWriter;
    private StudyDTO study;
    private LocalDate createDate;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public StudyMilestone toEntity(){
        return StudyMilestone.builder()
                .studyMileStoneTitle(studyMileStoneTitle)
                .studyMileStoneContent(studyMileStoneContent)
                .studyMilestoneStatus(studyMilestoneStatus)
                .build();
    }


    @QueryProjection
    public StudyMilestoneDTO(Long studyMileStoneId, String studyMileStoneTitle, String studyMileStoneContent, StudyMilestoneStatus studyMilestoneStatus, UserDTO mileStoneWriter, StudyDTO study) {
        this.studyMileStoneId = studyMileStoneId;
        this.studyMileStoneTitle = studyMileStoneTitle;
        this.studyMileStoneContent = studyMileStoneContent;
        this.studyMilestoneStatus = studyMilestoneStatus;
        this.mileStoneWriter = mileStoneWriter;
        this.study = study;
    }
}

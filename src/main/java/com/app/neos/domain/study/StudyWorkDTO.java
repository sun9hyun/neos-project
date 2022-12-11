package com.app.neos.domain.study;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyWork;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.work.StudyLocationStatus;
import com.app.neos.type.study.work.StudyWorkStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@NoArgsConstructor
@Data
public class StudyWorkDTO {
    private Long studyWorkId;
    private String studyWorkContent;
    private LocalDateTime studyWorkTargetDate;
    private String studyWorkLocation;
    private StudyLocationStatus studyLocationStatus;
    private StudyWorkStatus studyWorkStatus;
    private UserDTO studyWorkWriter;
    private UserDTO studyWorkChoiceMember;
    private StudyDTO study;

    public StudyWork toEntity(){
        return StudyWork.builder()
                .studyWorkContent(studyWorkContent)
                .studyWorkTargetDate(studyWorkTargetDate)
                .studyWorkLocation(studyWorkLocation)
                .studyWorkStatus(studyWorkStatus)
                .studyLocationStatus(studyLocationStatus)
                .build();
    }

    @QueryProjection
    public StudyWorkDTO(Long studyWorkId, String studyWorkContent, LocalDateTime studyWorkTargetDate, String studyWorkLocation, StudyLocationStatus studyLocationStatus, StudyWorkStatus studyWorkStatus, UserDTO studyWorkWriter, UserDTO studyWorkChoiceMember, StudyDTO study) {
        this.studyWorkId = studyWorkId;
        this.studyWorkContent = studyWorkContent;
        this.studyWorkTargetDate = studyWorkTargetDate;
        this.studyWorkLocation = studyWorkLocation;
        this.studyLocationStatus = studyLocationStatus;
        this.studyWorkStatus = studyWorkStatus;
        this.studyWorkWriter = studyWorkWriter;
        this.studyWorkChoiceMember = studyWorkChoiceMember;
        this.study = study;
    }
}

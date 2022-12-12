package com.app.neos.domain.study;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.StudyWork;
import com.app.neos.type.study.work.StudyLocationStatus;
import com.app.neos.type.study.work.StudyWorkStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
@NoArgsConstructor
@Data
public class StudyWorkDTO {
    private Long studyWorkId;
    private String studyWorkContent; //
    private LocalDate studyWorkTargetDate; //
    private String studyWorkLocation;
    private StudyLocationStatus studyLocationStatus; //
    private StudyWorkStatus studyWorkStatus;
    private UserDTO studyWorkWriter;    //
    private UserDTO studyWorkChoiceMember; //
    private StudyDTO study; //
    private LocalDate createDate; //
    private int dDay; //
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public StudyWork toEntity(){
        return StudyWork.builder()
                .studyWorkContent(studyWorkContent)
                .studyWorkTargetDate(studyWorkTargetDate.atStartOfDay())
                .studyWorkLocation(studyWorkLocation)
                .studyWorkStatus(studyWorkStatus)
                .studyLocationStatus(studyLocationStatus)
                .build();
    }

    @QueryProjection
    public StudyWorkDTO(Long studyWorkId, String studyWorkContent, LocalDate studyWorkTargetDate, String studyWorkLocation, StudyLocationStatus studyLocationStatus, StudyWorkStatus studyWorkStatus, UserDTO studyWorkWriter, UserDTO studyWorkChoiceMember, StudyDTO study, LocalDate createDate, int dDay) {
        this.studyWorkId = studyWorkId;
        this.studyWorkContent = studyWorkContent;
        this.studyWorkTargetDate = studyWorkTargetDate;
        this.studyWorkLocation = studyWorkLocation;
        this.studyLocationStatus = studyLocationStatus;
        this.studyWorkStatus = studyWorkStatus;
        this.studyWorkWriter = studyWorkWriter;
        this.studyWorkChoiceMember = studyWorkChoiceMember;
        this.study = study;
        this.createDate = createDate;
        this.dDay = dDay;
    }
}

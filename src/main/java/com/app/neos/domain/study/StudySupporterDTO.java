package com.app.neos.domain.study;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudySupporter;
import com.app.neos.type.study.supporter.StudySupporterStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@NoArgsConstructor
public class StudySupporterDTO {
    private Long studySupporterId;
    private StudySupporterStatus studySupporterStatus;
    private UserDTO user;
    private Long userId;
    private Long studyId;
    private LocalDate createdDate;


    public StudySupporter toEntity(){
        return StudySupporter.builder()
                .studySupporterStatus(studySupporterStatus)
                .build();
    }

    @QueryProjection
    public StudySupporterDTO(Long studySupporterId, StudySupporterStatus studySupporterStatus, UserDTO user, Long studyId) {
        this.studySupporterId = studySupporterId;
        this.studySupporterStatus = studySupporterStatus;
        this.user = user;
        this.studyId = studyId;
    }

    @QueryProjection
    public StudySupporterDTO(Long studySupporterId, StudySupporterStatus studySupporterStatus, Long userId, Long studyId) {
        this.studySupporterId = studySupporterId;
        this.studySupporterStatus = studySupporterStatus;
        this.userId = userId;
        this.studyId = studyId;
    }
}

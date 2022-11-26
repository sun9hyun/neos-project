package com.app.neos.domain.study;

import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudySupporter;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.supporter.StudySupporterStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StudySupporterDTO {
    private Long studySupporterId;
    private StudySupporterStatus studySupporterStatus;
    private User user;
    private Study study;


    public StudySupporter toEntity(){
        return StudySupporter.builder()
                .studySupporterStatus(studySupporterStatus)
                .build();
    }

    @QueryProjection
    public StudySupporterDTO(Long studySupporterId, StudySupporterStatus studySupporterStatus, User user, Study study) {
        this.studySupporterId = studySupporterId;
        this.studySupporterStatus = studySupporterStatus;
        this.user = user;
        this.study = study;
    }
}

package com.app.neos.entity.study;


import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.supporter.StudySupporterStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="TBL_STUDY_SUPPORTER")
@Getter @ToString(exclude = {"user","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudySupporter extends Created {
    @Id @GeneratedValue
    private Long studySupporterId;

    @Enumerated(EnumType.STRING) @NotNull
    private StudySupporterStatus studySupporterStatus;

    //지원자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STUDY_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Study study;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeStudy(Study study){
        this.study = study;
        study.getStudySupporterList().add(this);
    }

    @Builder
    public StudySupporter(@NotNull StudySupporterStatus studySupporterStatus) {
        this.studySupporterStatus = studySupporterStatus;
    }

    public void update(StudySupporterDTO studySupporterDTO){
        this.studySupporterStatus = studySupporterDTO.getStudySupporterStatus();
    }

    public StudySupporterDTO toDTO(){
        StudySupporterDTO dto = new StudySupporterDTO();
        dto.setStudySupporterId(this.studySupporterId);
        dto.setStudySupporterStatus(this.studySupporterStatus);
        dto.setStudyId(this.study.getStudyId());
        dto.setUser(this.user.toDTO());
        dto.setCreatedDate(this.getCreatedDate().toLocalDate());
        return dto;
    }
}

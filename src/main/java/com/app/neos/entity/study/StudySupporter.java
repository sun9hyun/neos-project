package com.app.neos.entity.study;


import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.supporter.StudySupporterStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_STUDY_SUPPORTER")
@Getter @ToString(exclude = {"user","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudySupporter extends Created {
    @Id @GeneratedValue
    private Long studySupporterId;

    @Enumerated(EnumType.STRING) @NonNull
    private StudySupporterStatus studySupporterStatus;

    //지원자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STUDY_ID")
    private Study study;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeStudy(Study study){
        this.study = study;
    }

    @Builder
    public StudySupporter(@NonNull StudySupporterStatus studySupporterStatus) {
        this.studySupporterStatus = studySupporterStatus;
    }

    public void update(StudySupporterDTO studySupporterDTO){
        this.studySupporterStatus = studySupporterDTO.getStudySupporterStatus();
    }

}

package com.app.neosproject.entity.study;


import com.app.neosproject.entity.period.Created;
import com.app.neosproject.entity.user.User;
import com.app.neosproject.type.study.supporter.StudySupporterStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_STUDY_SUPPORTER")
@Getter @ToString(exclude = {"user","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudySupporter extends Created {
    @Id @GeneratedValue
    private Long studySupporterId;

    @Enumerated(EnumType.STRING)
    private StudySupporterStatus studySupporterStatus;

    //지원자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STUDY_ID")
    private Study study;

    @Builder
    public StudySupporter(StudySupporterStatus studySupporterStatus, User user, Study study) {
        this.studySupporterStatus = studySupporterStatus;
        this.user = user;
        this.study = study;
    }
}

package com.app.neosproject.entity.study;


import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import com.app.neosproject.type.study.work.StudyLocationStatus;
import com.app.neosproject.type.study.work.StudyWorkStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_STUDY_WORK")
@Getter @ToString(exclude = {"studyWorkWriter", "studyWorkChoiceMember","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyWork extends Period {
    @Id @GeneratedValue
    private Long studyWorkId;

    private String studyWorkContent;
    private LocalDateTime studyWorkTargetDate;
    private String studyWorkLocation;

    @Enumerated(EnumType.STRING)
    private StudyLocationStatus studyLocationStatus;

    @Enumerated(EnumType.STRING)
    private StudyWorkStatus studyWorkStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="WRITER_USER_ID")
    private User studyWorkWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CHOICE_USER_ID")
    private User studyWorkChoiceMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_ID")
    private Study study;


    public void changeStudyWorkWriter(User studyWorkWriter){
        this.studyWorkWriter = studyWorkWriter;
    }

    public void changeStudyWorkChoiceMember(User studyWorkChoiceMember){
        this.studyWorkChoiceMember = studyWorkChoiceMember;
    }

    public void changeStudy(Study study){
        this.study = study;
    }

    @Builder
    public StudyWork(String studyWorkContent, LocalDateTime studyWorkTargetDate, String studyWorkLocation, StudyLocationStatus studyLocationStatus, StudyWorkStatus studyWorkStatus, User studyWorkWriter, User studyWorkChoiceMember, Study study) {
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

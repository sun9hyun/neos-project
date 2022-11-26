package com.app.neos.entity.study;


import com.app.neos.domain.study.StudyWorkDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.work.StudyLocationStatus;
import com.app.neos.type.study.work.StudyWorkStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_STUDY_WORK")
@Getter @ToString(exclude = {"studyWorkWriter", "studyWorkChoiceMember","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyWork extends Period {
    @Id @GeneratedValue @NonNull
    private Long studyWorkId;
    @NonNull
    private String studyWorkContent;
    @NonNull
    private LocalDateTime studyWorkTargetDate;
    @NonNull
    private String studyWorkLocation;

    @Enumerated(EnumType.STRING) @NonNull
    private StudyLocationStatus studyLocationStatus;

    @Enumerated(EnumType.STRING) @NonNull
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
    public StudyWork(@NonNull String studyWorkContent, @NonNull LocalDateTime studyWorkTargetDate, @NonNull String studyWorkLocation, @NonNull StudyLocationStatus studyLocationStatus, @NonNull StudyWorkStatus studyWorkStatus) {
        this.studyWorkContent = studyWorkContent;
        this.studyWorkTargetDate = studyWorkTargetDate;
        this.studyWorkLocation = studyWorkLocation;
        this.studyLocationStatus = studyLocationStatus;
        this.studyWorkStatus = studyWorkStatus;
    }

    public void statusUpdate(StudyWorkDTO studyWorkDTO){
        this.studyWorkStatus = studyWorkDTO.getStudyWorkStatus();
    }
}

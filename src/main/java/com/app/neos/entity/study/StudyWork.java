package com.app.neos.entity.study;


import com.app.neos.domain.study.StudyWorkDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.work.StudyLocationStatus;
import com.app.neos.type.study.work.StudyWorkStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_STUDY_WORK")
@Getter @ToString(exclude = {"studyWorkWriter", "studyWorkChoiceMember","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyWork extends Period {
    @Id @GeneratedValue
    private Long studyWorkId;
    @NotNull
    private String studyWorkContent;
    @NotNull
    private LocalDateTime studyWorkTargetDate;

    private String studyWorkLocation;

    @Enumerated(EnumType.STRING) @NotNull
    private StudyLocationStatus studyLocationStatus;

    @Enumerated(EnumType.STRING) @NotNull
    private StudyWorkStatus studyWorkStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="WRITER_USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User studyWorkWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CHOICE_USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User studyWorkChoiceMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
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
    public StudyWork(@NotNull String studyWorkContent, @NotNull LocalDateTime studyWorkTargetDate, String studyWorkLocation, @NotNull StudyLocationStatus studyLocationStatus, @NotNull StudyWorkStatus studyWorkStatus) {
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

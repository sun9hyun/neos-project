package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyNewsDTO;
import com.app.neos.entity.period.Created;
import com.app.neos.entity.period.Period;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_study_news")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyNews extends Period {
    @Id @GeneratedValue
    private Long studyNewsId;


    private String category;
    private LocalDate newsCreatedTime;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STUDY_MEMBER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StudyMember studyMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STUDY_MILE_STONE_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StudyMilestone studyMilestone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STUDY_WORK_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StudyWork studyWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Study study;


    public void changeStudyMember(StudyMember studyMember){
        this.studyMember = studyMember;
    }

    public void changeStudyMileStone(StudyMilestone studyMilestone){
        this.studyMilestone = studyMilestone;
    }
    public void changeStudyWork(StudyWork studyWork){
        this.studyWork=studyWork;
    }

    public void changeStudy(Study study){
        this.study = study;
    }

    @Builder
    public StudyNews(String category, LocalDate newsCreatedTime, String status) {
        this.status = status;
        this.category = category;
        this.newsCreatedTime = newsCreatedTime;
    }

    public StudyNewsDTO toDTO(){
        StudyNewsDTO dto = new StudyNewsDTO();
        dto.setStudyNewsId(studyNewsId);
        dto.setCategory(category);
        dto.setNewsCreatedTime(newsCreatedTime);
        if(studyMember != null){
            dto.setStudyMember(studyMember.toDTO());
        }
        if(studyMilestone != null){
            dto.setStudyMilestone(studyMilestone.toDTO());
        }
        if(studyWork != null){
            dto.setStudyWork(studyWork.toDTO());
        }
        dto.setStudyDTO(study.toDTO());
        dto.setStatus(status);
        return dto;
    }

    public void update(LocalDate localDate){
        this.newsCreatedTime = localDate;
        this.status="FINISH";
    }


}

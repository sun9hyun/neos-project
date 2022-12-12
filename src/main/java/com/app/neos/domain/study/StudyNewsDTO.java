package com.app.neos.domain.study;

import com.app.neos.entity.study.StudyMember;
import com.app.neos.entity.study.StudyMilestone;
import com.app.neos.entity.study.StudyNews;
import com.app.neos.entity.study.StudyWork;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class StudyNewsDTO {

    private Long studyNewsId;
    private String category;
    private LocalDate newsCreatedTime;
    private StudyMemberDTO studyMember;
    private StudyMilestoneDTO studyMilestone;
    private StudyWorkDTO studyWork;
    private StudyDTO studyDTO;
    private String status;

    public StudyNews toEntity(){
        return StudyNews.builder().category(category).newsCreatedTime(newsCreatedTime).status(status).build();
    }

    @QueryProjection
    public StudyNewsDTO(Long studyNewsId, String category, LocalDate newsCreatedTime, StudyMemberDTO studyMember, StudyMilestoneDTO studyMilestone, StudyWorkDTO studyWork, StudyDTO studyDTO) {
        this.studyNewsId = studyNewsId;
        this.category = category;
        this.newsCreatedTime = newsCreatedTime;
        this.studyMember = studyMember;
        this.studyMilestone = studyMilestone;
        this.studyWork = studyWork;
        this.studyDTO = studyDTO;
    }
}


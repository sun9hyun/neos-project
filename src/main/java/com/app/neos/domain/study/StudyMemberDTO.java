package com.app.neos.domain.study;


import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyMember;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.member.StudyMemberStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StudyMemberDTO {

    private Long studyMemberId;
    private StudyMemberStatus studyMemberStatus;
    private User user;
    private Study study;

    public StudyMember toEntity(){
        return StudyMember.builder()
                .studyMemberStatus(studyMemberStatus)
                .build();
    }

    @QueryProjection
    public StudyMemberDTO(Long studyMemberId, StudyMemberStatus studyMemberStatus, User user, Study study) {
        this.studyMemberId = studyMemberId;
        this.studyMemberStatus = studyMemberStatus;
        this.user = user;
        this.study = study;
    }
}

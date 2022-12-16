package com.app.neos.domain.study;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.StudyMember;
import com.app.neos.type.study.member.StudyMemberStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class StudyMemberDTO {

    private Long studyMemberId;
    private StudyMemberStatus studyMemberStatus;
    private UserDTO userDTO;
    private Long userId;
    private Long studyId;
    private LocalDate creatDate;
    private LocalDateTime createdTime;


    public StudyMember toEntity(){
        return StudyMember.builder()
                .studyMemberStatus(studyMemberStatus)
                .build();
    }



    @QueryProjection
    public StudyMemberDTO(Long studyMemberId, StudyMemberStatus studyMemberStatus, UserDTO userDTO, Long studyId) {
        this.studyMemberId = studyMemberId;
        this.studyMemberStatus = studyMemberStatus;
        this.userDTO = userDTO;
        this.studyId = studyId;
    }

    @QueryProjection
    public StudyMemberDTO(Long studyMemberId, StudyMemberStatus studyMemberStatus, Long userId, Long studyId) {
        this.studyMemberId = studyMemberId;
        this.studyMemberStatus = studyMemberStatus;
        this.userId = userId;
        this.studyId = studyId;
    }
}

package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.member.StudyMemberStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_MEMBER")
@Getter @ToString(exclude = {"user","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyMember extends Created {
    @Id @GeneratedValue
    private Long studyMemberId;

    @Enumerated(EnumType.STRING) @NotNull
   private StudyMemberStatus studyMemberStatus;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="USER_ID")
   @OnDelete(action = OnDeleteAction.CASCADE)
   private User user;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="STUDY_ID")
   @OnDelete(action = OnDeleteAction.CASCADE)
   private Study study;

   public void changeUser(User user){
       this.user = user;

   }
   public void changeStudy(Study study){
       this.study= study;
       study.getStudyMemberList().add(this);
   }

   @Builder
    public StudyMember(@NotNull StudyMemberStatus studyMemberStatus) {
        this.studyMemberStatus = studyMemberStatus;
    }

    public StudyMemberDTO toDTO(){
        StudyMemberDTO dto = new StudyMemberDTO();
        dto.setStudyMemberId(this.studyMemberId);
        dto.setStudyMemberStatus(this.studyMemberStatus);
        dto.setStudyDTO(this.study.toDTO());
        dto.setUserDTO(this.user.toDTO());
        return dto;
    }
}

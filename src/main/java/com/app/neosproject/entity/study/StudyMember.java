package com.app.neosproject.entity.study;

import com.app.neosproject.entity.period.Created;
import com.app.neosproject.entity.user.User;
import com.app.neosproject.type.study.member.StudyMemberStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_MEMBER")
@Getter @ToString(exclude = {"user","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyMember extends Created {
    @Id @GeneratedValue
    private Long studyMemberId;

   private StudyMemberStatus studyMemberStatus;

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
       this.study= study;
   }

    @Builder

    public StudyMember(StudyMemberStatus studyMemberStatus, User user, Study study) {
        this.studyMemberStatus = studyMemberStatus;
        this.user = user;
        this.study = study;
    }
}

package com.app.neos.entity.study;

import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.member.StudyMemberStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_MEMBER")
@Getter @ToString(exclude = {"user","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyMember extends Created {
    @Id @GeneratedValue @NonNull
    private Long studyMemberId;

    @Enumerated(EnumType.STRING) @NonNull
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
    public StudyMember(@NonNull StudyMemberStatus studyMemberStatus) {
        this.studyMemberStatus = studyMemberStatus;
    }
}

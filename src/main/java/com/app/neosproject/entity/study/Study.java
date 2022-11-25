package com.app.neosproject.entity.study;

import com.app.neosproject.embeddable.study.StudyField;
import com.app.neosproject.embeddable.study.StudyOnlineWhether;
import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import com.app.neosproject.type.study.StudyRecruitStatus;
import com.app.neosproject.type.study.StudyStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="TBL_STUDY")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study extends Period {
    @Id @GeneratedValue
    private Long studyId;

   private String studyTitle;
   @Embedded
   private StudyField studyField;

   @Embedded
   private StudyOnlineWhether studyOnlineWhether;

   private Integer studySupport;


   private StudyRecruitStatus studyRecruitStatus;
   private StudyStatus studyStatus;

   private String studyContent;
   private Integer studyView;
   private LocalDateTime studyEndDate;

//   작성자
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="USER_ID")
   private User user;

   public void changeUser(User user){
      this.user = user;
   }

   @Builder
   public Study(String studyTitle, StudyField studyField, StudyOnlineWhether studyOnlineWhether, Integer studySupport, StudyRecruitStatus studyRecruitStatus, StudyStatus studyStatus, String studyContent, Integer studyView, LocalDateTime studyEndDate, User user) {
      this.studyTitle = studyTitle;
      this.studyField = studyField;
      this.studyOnlineWhether = studyOnlineWhether;
      this.studySupport = studySupport;
      this.studyRecruitStatus = studyRecruitStatus;
      this.studyStatus = studyStatus;
      this.studyContent = studyContent;
      this.studyView = studyView;
      this.studyEndDate = studyEndDate;
      this.user = user;
   }
}

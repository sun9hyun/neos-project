package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.embeddable.study.StudyField;
import com.app.neos.embeddable.study.StudyOnlineWhether;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.StudyRecruitStatus;
import com.app.neos.type.study.StudyStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="TBL_STUDY")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study extends Period {
    @Id @GeneratedValue @NonNull
    private Long studyId;
   @NonNull
   private String studyTitle;

   @Embedded @NonNull
   private StudyField studyField;

   @Embedded @NonNull
   private StudyOnlineWhether studyOnlineWhether;

   @NonNull
   private Integer studySupport;

   @Enumerated(EnumType.STRING) @NonNull
   private StudyRecruitStatus studyRecruitStatus;

   @Enumerated(EnumType.STRING) @NonNull
   private StudyStatus studyStatus;
   @NonNull
   private String studyContent;
   @NonNull
   private int studyView;
   @NonNull
   private LocalDateTime studyEndDate;

//   작성자
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="USER_ID")
   private User user;

   public void changeUser(User user){
      this.user = user;
   }

   @Builder
   public Study(@NonNull String studyTitle, @NonNull StudyField studyField, @NonNull StudyOnlineWhether studyOnlineWhether, @NonNull Integer studySupport, @NonNull StudyRecruitStatus studyRecruitStatus, @NonNull StudyStatus studyStatus, @NonNull String studyContent, @NonNull LocalDateTime studyEndDate) {
      this.studyTitle = studyTitle;
      this.studyField = studyField;
      this.studyOnlineWhether = studyOnlineWhether;
      this.studySupport = studySupport;
      this.studyRecruitStatus = studyRecruitStatus;
      this.studyStatus = studyStatus;
      this.studyContent = studyContent;
      this.studyEndDate = studyEndDate;
   }

   public void update(StudyDTO studyDTO){
      StudyField studyField = StudyField.builder().studyKeyword(studyDTO.getStudyKeyword()).studyType(studyDTO.getStudyType()).build();
      StudyOnlineWhether studyOnlineWhether = StudyOnlineWhether.builder()
              .studyCity(studyDTO.getStudyCity()).studyO2o(studyDTO.getStudyO2o()).build();
      this.studyTitle = studyDTO.getStudyTitle();
      this.studyField = studyField;
      this.studyOnlineWhether= studyOnlineWhether;
      this.studySupport = studyDTO.getStudySupport();
      this.studyRecruitStatus = studyDTO.getStudyRecruitStatus();
      this.studyStatus = studyDTO.getStudyStatus();
      this.studyContent = studyDTO.getStudyContent();
      this.studyEndDate = studyDTO.getStudyEndDate();
   }

   public void viewUpdate(){
      this.studyView = studyView+1;
   }
}

package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.embeddable.study.StudyField;
import com.app.neos.embeddable.study.StudyOnlineWhether;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.StudyRecruitStatus;
import com.app.neos.type.study.StudyStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="TBL_STUDY")
@Getter @ToString(exclude = {"user","followList","studyMemberList","studySupporterList"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study extends Period {
    @Id @GeneratedValue
    private Long studyId;
   @NotNull
   private String studyTitle;

   @Embedded @NotNull
   private StudyField studyField;

   @Embedded @NotNull
   private StudyOnlineWhether studyOnlineWhether;

   @NotNull
   private Integer studySupport;

   @Enumerated(EnumType.STRING) @NotNull
   private StudyRecruitStatus studyRecruitStatus;

   @Enumerated(EnumType.STRING) @NotNull
   private StudyStatus studyStatus;
   @NotNull
   private String studyContent;
   @NotNull
   private int studyView;

   @OneToMany(fetch = FetchType.LAZY,mappedBy = "study", cascade = CascadeType.REMOVE)
   private List<StudyFollow> followList;

   @OneToMany(fetch = FetchType.LAZY,mappedBy = "study",cascade = CascadeType.REMOVE)
   private List<StudyMember> studyMemberList;

   @OneToMany(fetch = FetchType.LAZY,mappedBy = "study",cascade = CascadeType.REMOVE)
   @JsonIgnore
   public List<StudySupporter> studySupporterList;

   private LocalDate studyEndDate;

//   작성자
   @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name="USER_ID")
   private User user;

   public void changeFollowList(List<StudyFollow> followList){
      this.followList = followList;
   }

   public void changeStudyMemberList(List<StudyMember> studyMembers){
         this.studyMemberList = studyMembers;
   }

   public void changeUser(User user){
      this.user = user;
   }

   @Builder
   public Study(@NotNull String studyTitle, @NotNull StudyField studyField, @NotNull StudyOnlineWhether studyOnlineWhether, @NotNull Integer studySupport, @NotNull StudyRecruitStatus studyRecruitStatus, @NotNull StudyStatus studyStatus, @NotNull String studyContent, LocalDate studyEndDate) {
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
      this.studyStatus = studyDTO.getStudyStatus();
      this.studyContent = studyDTO.getStudyContent();
      this.studyEndDate = studyDTO.getStudyEndDate();
   }

   public void viewUpdate(){
      this.studyView = studyView+1;
   }


//   private String collegeName;

   public StudyDTO toDTO(){
      StudyDTO studyDTO = new StudyDTO();
      studyDTO.setStudyId(this.studyId);
      studyDTO.setStudyTitle(this.studyTitle);
      studyDTO.setStudyType(this.studyField.getStudyType());
      studyDTO.setStudyKeyword(this.studyField.getStudyKeyword());
      studyDTO.setStudyO2o(this.studyOnlineWhether.getStudyO2o());
      studyDTO.setStudyCity(this.studyOnlineWhether.getStudyCity());
      studyDTO.setStudySupport(this.studySupport);
      studyDTO.setStudyRecruitStatus(this.studyRecruitStatus);
      studyDTO.setStudyStatus(this.studyStatus);
      studyDTO.setStudyContent(this.studyContent);
      studyDTO.setStudyView(this.studyView);
      studyDTO.setStudyEndDate(this.studyEndDate);
      studyDTO.setUserId(this.user.getUserId());
      studyDTO.setUserNickName(this.user.getUserNickName());
      studyDTO.setCollegeId(this.user.getCollege().getCollegeId());
      studyDTO.setCollegeLogoFile(this.user.getCollege().getCollegeLogoFile());
      studyDTO.setCollegeName(this.user.getCollege().getCollegeName());
      studyDTO.setStudyStartDate(this.getCreatedDate().toLocalDate());
      studyDTO.setFollowTotal(this.followList.size());
      List<UserDTO> follower = this.followList.stream().map(i->i.getUser().toDTO()).collect(Collectors.toList());
      List<StudyMemberDTO> studyMembers = this.studyMemberList.stream().map(i->i.toDTO()).collect(Collectors.toList());
      List<Long> followerNumberList = this.followList.stream().map(i->i.getUser().getUserId()).collect(Collectors.toList());
      List<StudySupporterDTO>  studySupporterDTOS = this.studySupporterList.stream().map(i->i.toDTO()).collect(Collectors.toList());
      studyDTO.setStudyMemberList(studyMembers);
      studyDTO.setFollower(follower);
      studyDTO.setFollowerNumberList(followerNumberList);
      studyDTO.setStudySupporterDTOS(studySupporterDTOS);
      return studyDTO;
   }


}

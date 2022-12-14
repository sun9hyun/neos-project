package com.app.neos.domain.study;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.embeddable.study.StudyField;
import com.app.neos.embeddable.study.StudyOnlineWhether;
import com.app.neos.entity.study.Study;
import com.app.neos.type.study.StudyRecruitStatus;
import com.app.neos.type.study.StudyStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class StudyDTO {
    private Long studyId;
    private String studyTitle;
//    임베디드1
    private String studyType;
    private String studyKeyword;
//    임베디드2
    private String studyO2o;
    private String studyCity;
//모집인원
    private Integer studySupport;
//    모집상태
    private StudyRecruitStatus studyRecruitStatus;
// 준비중 진행중 완료
    private StudyStatus studyStatus;

    private String studyContent;
    private int studyView;
    private LocalDate studyEndDate;
    private LocalDate studyStartDate;
    private Long collegeId;
    private String collegeLogoFile;
    private String collegeName;
    private Long userId;
    private String userNickName;
    private int followTotal;
    private List<Long> followerNumberList;
    private List<UserDTO> follower;
    private List<StudyMemberDTO> studyMemberList;
    private List<StudySupporterDTO> studySupporterDTOS;

    private LocalDateTime createdDate;

    public Study toEntity(){
       StudyField studyField =  StudyField.builder().studyType(studyType).studyKeyword(studyKeyword).build();
        StudyOnlineWhether studyOnlineWhether = StudyOnlineWhether.builder().studyO2o(studyO2o).studyCity(studyCity).build();

        return Study.builder()
                .studyTitle(studyTitle)
                .studySupport(studySupport)
                .studyRecruitStatus(studyRecruitStatus)
                .studyStatus(studyStatus)
                .studyContent(studyContent)
                .studyEndDate(studyEndDate)
                .studyField(studyField)
                .studyOnlineWhether(studyOnlineWhether)
                .build();
    }

    @QueryProjection
    public StudyDTO(Long studyId, String studyTitle, String studyType, String studyKeyword, String studyO2o, String studyCity, Integer studySupport, StudyRecruitStatus studyRecruitStatus, StudyStatus studyStatus, String studyContent, int studyView, LocalDate studyEndDate) {
        this.studyId = studyId;
        this.studyTitle = studyTitle;
        this.studyType = studyType;
        this.studyKeyword = studyKeyword;
        this.studyO2o = studyO2o;
        this.studyCity = studyCity;
        this.studySupport = studySupport;
        this.studyRecruitStatus = studyRecruitStatus;
        this.studyStatus = studyStatus;
        this.studyContent = studyContent;
        this.studyView = studyView;
        this.studyEndDate = studyEndDate;
    }

    @QueryProjection
    public StudyDTO(Long studyId, String studyTitle, String studyType, String studyKeyword, String studyO2o, String studyCity, Integer studySupport, StudyRecruitStatus studyRecruitStatus, StudyStatus studyStatus, String studyContent, int studyView, LocalDate studyEndDate, Long collegeId, String collegeLogoFile, String collegeName, Long userId, String userNickName) {
        this.studyId = studyId;
        this.studyTitle = studyTitle;
        this.studyType = studyType;
        this.studyKeyword = studyKeyword;
        this.studyO2o = studyO2o;
        this.studyCity = studyCity;
        this.studySupport = studySupport;
        this.studyRecruitStatus = studyRecruitStatus;
        this.studyStatus = studyStatus;
        this.studyContent = studyContent;
        this.studyView = studyView;
        this.studyEndDate = studyEndDate;
        this.collegeId = collegeId;
        this.collegeLogoFile = collegeLogoFile;
        this.collegeName = collegeName;
        this.userId = userId;
        this.userNickName = userNickName;
    }

    @QueryProjection
    public StudyDTO(Long studyId, String studyTitle, String studyType, String studyKeyword, String studyO2o, String studyCity, Integer studySupport, StudyRecruitStatus studyRecruitStatus, StudyStatus studyStatus, String studyContent, int studyView, LocalDate studyEndDate, Long collegeId, String collegeLogoFile, String collegeName, Long userId, String userNickName, LocalDateTime createdDate) {
        this.studyId = studyId;
        this.studyTitle = studyTitle;
        this.studyType = studyType;
        this.studyKeyword = studyKeyword;
        this.studyO2o = studyO2o;
        this.studyCity = studyCity;
        this.studySupport = studySupport;
        this.studyRecruitStatus = studyRecruitStatus;
        this.studyStatus = studyStatus;
        this.studyContent = studyContent;
        this.studyView = studyView;
        this.studyEndDate = studyEndDate;
        this.collegeId = collegeId;
        this.collegeLogoFile = collegeLogoFile;
        this.collegeName = collegeName;
        this.userId = userId;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public StudyDTO(Long studyId, String studyTitle, String userNickName, LocalDateTime createdDate, int followTotal) {
        this.studyId = studyId;
        this.studyTitle = studyTitle;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
        this.followTotal = followTotal;
    }

    @QueryProjection
    public StudyDTO(Long studyId, int studyView, String studyTitle, String studyType, String userNickName, LocalDateTime createdDate) {
        this.studyId = studyId;
        this.studyView = studyView;
        this.studyTitle = studyTitle;
        this.studyType = studyType;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
    }
}

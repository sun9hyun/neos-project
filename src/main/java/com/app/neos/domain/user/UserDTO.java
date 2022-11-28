package com.app.neos.domain.user;


import com.app.neos.embeddable.user.UserCollegeInfo;
import com.app.neos.embeddable.user.UserLike;
import com.app.neos.embeddable.user.UserMBTI;
import com.app.neos.embeddable.user.UserNeosPower;
import com.app.neos.entity.college.College;
import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.user.User;
import com.app.neos.type.user.UserCollegeMajor;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String userNickName;
    private String userOAuthId;
    private String userOAuthEmail;
    private String userCollegeEmail;
    private String userPhoneNumber;
    //임베디드
    private boolean userCollegeCertify;
    private Integer userCollegeYear;
    private UserCollegeMajor userCollegeMajor;
    //임베디드
    private String userNeosBadge;
    private Integer userNeosPowerLevel;
    private Integer userNeosPowerAbility;

    private Integer userNeosPoint;
    private Integer userChattingPoint;
    //임베디드
    private String userO2o;
    private String userCity;
    private String userDay;
    private String userTime;
    //임베디드
    private String userMbtiName;
    private String userMbtiColor;

    private String userIntroduce;
    private String userFile;

    private College college;



    public User toEntity(){
        UserCollegeInfo userCollegeInfo = UserCollegeInfo.builder().userCollegeYear(userCollegeYear).userCollegeMajor(userCollegeMajor).build();
        UserLike userLike = UserLike.builder().userCity(userCity).userDay(userDay).userO2o(userO2o).userTime(userTime).build();
        UserMBTI userMBTI = UserMBTI.builder().userMbtiColor(userMbtiColor).userMbtiName(userMbtiName).build();
        UserNeosPower userNeosPower = UserNeosPower.builder().userNeosBadge(userNeosBadge).userNeosPowerAbility(userNeosPowerAbility).userNeosPowerLevel(userNeosPowerLevel).build();

        return User.builder().userChattingPoint(userChattingPoint)
                .userCollegeEmail(userCollegeEmail)
                .userCollegeInfo(userCollegeInfo)
                .userFile(userFile)
                .userIntroduce(userIntroduce)
                .userLike(userLike)
                .userMBTI(userMBTI)
                .userNeosPoint(userNeosPoint)
                .userNeosPower(userNeosPower)
                .userNickName(userNickName)
                .userOAuthEmail(userOAuthEmail)
                .userOAuthId(userOAuthId)
                .userPhoneNumber(userPhoneNumber)
                .userCollegeCertify(userCollegeCertify)
                .build();

    }

    @QueryProjection

    public UserDTO(String userNickName, String userOAuthId, String userOAuthEmail, String userCollegeEmail, String userPhoneNumber, boolean userCollegeCertify, Integer userCollegeYear, UserCollegeMajor userCollegeMajor, String userNeosBadge, Integer userNeosPowerLevel, Integer userNeosPowerAbility, Integer userNeosPoint, Integer userChattingPoint, String userO2o, String userCity, String userDay, String userTime, String userMbtiName, String userMbtiColor, String userIntroduce, String userFile, College college) {
        this.userNickName = userNickName;
        this.userOAuthId = userOAuthId;
        this.userOAuthEmail = userOAuthEmail;
        this.userCollegeEmail = userCollegeEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userCollegeCertify = userCollegeCertify;
        this.userCollegeYear = userCollegeYear;
        this.userCollegeMajor = userCollegeMajor;
        this.userNeosBadge = userNeosBadge;
        this.userNeosPowerLevel = userNeosPowerLevel;
        this.userNeosPowerAbility = userNeosPowerAbility;
        this.userNeosPoint = userNeosPoint;
        this.userChattingPoint = userChattingPoint;
        this.userO2o = userO2o;
        this.userCity = userCity;
        this.userDay = userDay;
        this.userTime = userTime;
        this.userMbtiName = userMbtiName;
        this.userMbtiColor = userMbtiColor;
        this.userIntroduce = userIntroduce;
        this.userFile = userFile;
        this.college = college;
    }
}

package com.app.neos.entity.user;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.embeddable.user.UserCollegeInfo;
import com.app.neos.embeddable.user.UserLike;
import com.app.neos.embeddable.user.UserMBTI;
import com.app.neos.embeddable.user.UserNeosPower;
import com.app.neos.entity.college.College;
import com.app.neos.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_USER")
@Getter @ToString(exclude = "college")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class User extends Period {
    @Id @GeneratedValue
    private Long userId;
    @NotNull
    private String userNickName;
    @NotNull
    private String userOAuthId;
    @NotNull
    private String userOAuthEmail;

    private String userCollegeEmail;
    private String userPhoneNumber;

    @NotNull
    private boolean userCollegeCertify;

    @Embedded
    private UserCollegeInfo userCollegeInfo;

    @Embedded @NotNull
    private UserNeosPower userNeosPower;
    @NotNull
    private Integer userNeosPoint;
    @NotNull
    private Integer userChattingPoint;

    @Embedded
    private UserLike userLike;

    @Embedded
    private UserMBTI userMBTI;
    @NotNull
    private String userIntroduce;

    private String userFile;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="COLLEGE_ID")
    private College college;

   public void changeCollege(College college){
       this.college =college;
   }
   @Builder
    public User(@NotNull String userNickName, @NotNull String userOAuthId, @NotNull String userOAuthEmail, String userCollegeEmail, String userPhoneNumber, @NotNull boolean userCollegeCertify, UserCollegeInfo userCollegeInfo, @NotNull UserNeosPower userNeosPower, @NotNull Integer userNeosPoint, @NotNull Integer userChattingPoint, UserLike userLike, UserMBTI userMBTI, @NotNull String userIntroduce, String userFile) {
        this.userNickName = userNickName;
        this.userOAuthId = userOAuthId;
        this.userOAuthEmail = userOAuthEmail;
        this.userCollegeEmail = userCollegeEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userCollegeCertify = userCollegeCertify;
        this.userCollegeInfo = userCollegeInfo;
        this.userNeosPower = userNeosPower;
        this.userNeosPoint = userNeosPoint;
        this.userChattingPoint = userChattingPoint;
        this.userLike = userLike;
        this.userMBTI = userMBTI;
        this.userIntroduce = userIntroduce;
        this.userFile = userFile;
    }

    //    대학교


    public void updateCertify(UserDTO userDTO){
        this.userCollegeCertify = userDTO.isUserCollegeCertify();
    }

    public void updatePower(UserDTO userDTO){
       UserNeosPower userNeosPower = UserNeosPower.builder().userNeosPowerLevel(userDTO.getUserNeosPowerLevel()).userNeosPowerAbility(userDTO.getUserNeosPowerAbility()).userNeosBadge(userDTO
       .getUserNeosBadge()).build();
       this.userNeosPower = userNeosPower;
    }

    public void updatePoint(UserDTO userDTO){
       this.userNeosPoint = userDTO.getUserNeosPoint();
    }

    public void update(UserDTO userDTO){
        UserCollegeInfo userCollegeInfo = UserCollegeInfo.builder().userCollegeMajor(userDTO.getUserCollegeMajor()).userCollegeYear(userDTO.getUserCollegeYear()).build();
        UserLike userLike = UserLike.builder().userCity(userDTO.getUserCity()).userO2o(userDTO.getUserO2o()).userDay(userDTO.getUserDay()).userTime(userDTO.getUserTime()).
                build();
        UserMBTI userMBTI = UserMBTI.builder().userMbtiColor(userDTO.getUserMbtiColor()).userMbtiName(userDTO.getUserMbtiName()).build();
       this.userNickName = userDTO.getUserNickName();
        this.userCollegeEmail = userDTO.getUserCollegeEmail();
        this.userCollegeInfo = userCollegeInfo;
        this.userChattingPoint = userDTO.getUserChattingPoint();
        this.userLike = userLike;
        this.userFile = userDTO.getUserFile();
        this.userIntroduce = userDTO.getUserIntroduce();
        this.userMBTI = userMBTI;
    }


}

package com.app.neosproject.entity.user;


import com.app.neosproject.embeddable.user.UserCollegeInfo;
import com.app.neosproject.embeddable.user.UserLike;
import com.app.neosproject.embeddable.user.UserMBTI;
import com.app.neosproject.embeddable.user.UserNeosPower;
import com.app.neosproject.entity.college.College;
import com.app.neosproject.entity.period.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_USER")
@Getter @ToString(exclude = "college")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Period {
    @Id @GeneratedValue
    private Long userId;

   private String userNickName;
   private String userOAuthId;
   private String userOAuthEmail;
   private String userCollegeEmail;
   private String userPhoneNumber;

    @Embedded
    private UserCollegeInfo userCollegeInfo;

    @Embedded
    private UserNeosPower userNeosPower;

   private Integer userNeosPoint;

   private Integer userChattingPoint;

  @Embedded
  private UserLike userLike;

  @Embedded
  private UserMBTI userMBTI;

   private String userIntroduce;
   private String userFile;


//   질문
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="COLLEGE_ID")
    private College college;

   public void changeCollege(College college){
       this.college =college;
   }

//    대학교

    @Builder
    public User(String userNickName, String userOAuthId, String userOAuthEmail, String userCollegeEmail, String userPhoneNumber, UserCollegeInfo userCollegeInfo, UserNeosPower userNeosPower, Integer userNeosPoint, Integer userChattingPoint, UserLike userLike, UserMBTI userMBTI, String userIntroduce, String userFile, College college) {
        this.userNickName = userNickName;
        this.userOAuthId = userOAuthId;
        this.userOAuthEmail = userOAuthEmail;
        this.userCollegeEmail = userCollegeEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userCollegeInfo = userCollegeInfo;
        this.userNeosPower = userNeosPower;
        this.userNeosPoint = userNeosPoint;
        this.userChattingPoint = userChattingPoint;
        this.userLike = userLike;
        this.userMBTI = userMBTI;
        this.userIntroduce = userIntroduce;
        this.userFile = userFile;
        this.college = college;
    }
}

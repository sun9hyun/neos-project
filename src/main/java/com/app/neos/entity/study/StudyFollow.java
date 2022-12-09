package com.app.neos.entity.study;


import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="TBL_STUDY_FOLLOW")
@Getter @ToString(exclude = {"study","user"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyFollow extends Created {
    @Id @GeneratedValue
    private Long studyFollowId;

//    스터디번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Study study;

//    구독한 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeStudy(Study study){
        this.study = study;
        study.getFollowList().add(this);
    }

    public static StudyFollow create(){
        return new StudyFollow();
    }

}

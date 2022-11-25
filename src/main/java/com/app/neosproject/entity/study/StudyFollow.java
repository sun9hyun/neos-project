package com.app.neosproject.entity.study;


import com.app.neosproject.entity.period.Created;
import com.app.neosproject.entity.user.User;
import lombok.*;

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
    private Study study;

//    구독한 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeStudy(Study study){
        this.study = study;
    }

    @Builder
    public StudyFollow(Study study, User user) {
        this.study = study;
        this.user = user;
    }
}

package com.app.neosproject.entity.counseling;

import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COUNSELING")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Counseling extends Period {

    @Id @GeneratedValue
    private Long counselingId;

    private String counselingTitle;
    private String counselingContent;
    private Integer counselingLikeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public Counseling(String counselingTitle, String counselingContent, Integer counselingLikeCount, User user) {
        this.counselingTitle = counselingTitle;
        this.counselingContent = counselingContent;
        this.counselingLikeCount = counselingLikeCount;
        this.user = user;
    }
}

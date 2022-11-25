package com.app.neosproject.entity.community;


import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends Period {
    @Id @GeneratedValue
    private Long communityId;
    private String communityTitle;
    private String communityContent;
    private Integer communityLikeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void changeUser(User user){
       this.user = user;
    }

    @Builder
    public Community(String communityTitle, String communityContent, Integer communityLikeCount, User user) {
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityLikeCount = communityLikeCount;
        this.user = user;
    }
}

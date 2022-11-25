package com.app.neosproject.entity.counseling;


import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity 
@Table(name = "TBL_COUNSELING_REPLY")
@Getter @ToString(exclude = {"user", "counseling"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselingReply extends Period {
    @Id @GeneratedValue
    private Long counselingReplyId;
    private String counselingReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNSELING_ID")
    private Counseling counseling;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeCounseling(Counseling counseling){
        this.counseling = counseling;
    }

    @Builder
    public CounselingReply(String counselingReplyContent, User user, Counseling counseling) {
        this.counselingReplyContent = counselingReplyContent;
        this.user = user;
        this.counseling = counseling;
    }
}

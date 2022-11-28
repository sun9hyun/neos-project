package com.app.neos.entity.counseling;


import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity 
@Table(name = "TBL_COUNSELING_REPLY")
@Getter @ToString(exclude = {"user", "counseling"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselingReply extends Period {
    @Id @GeneratedValue
    private Long counselingReplyId;
    @NotNull
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
    public CounselingReply(@NotNull String counselingReplyContent) {
        this.counselingReplyContent = counselingReplyContent;
    }

    public void update(CounselingReplyDTO counselingReplyDTO){
        this.counselingReplyContent = counselingReplyDTO.getCounselingReplyContent();
    }
}

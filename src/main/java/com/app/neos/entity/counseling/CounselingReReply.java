package com.app.neos.entity.counseling;

import com.app.neos.domain.counseling.CounselingReReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COUNSELING_REREPLY")
@Getter @ToString(exclude = {"user", "counselingReply"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselingReReply extends Period {
    @Id @GeneratedValue
    private Long counselingReReplyId;
    @NonNull
    private String counselingReReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNSELING_REPLY_ID")
    private CounselingReply counselingReply;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeCounselingReply(CounselingReply counselingReply){
        this.counselingReply = counselingReply;
    }

    @Builder
    public CounselingReReply(@NonNull String counselingReReplyContent) {
        this.counselingReReplyContent = counselingReReplyContent;
    }

    public void update(CounselingReReplyDTO counselingReReplyDTO){
        this. counselingReReplyContent = counselingReReplyDTO.getCounselingReReplyContent();
    }
}

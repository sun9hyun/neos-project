package com.app.neosproject.entity.store;

import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE_REREPLY")
@Getter @ToString(exclude = {"user", "storeReply"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreReReply extends Period {
    @Id @GeneratedValue
    private Long storeReReplyId;

    private Long storeReReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_REPLY_ID")
    private StoreReply storeReply;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeStoreReply(StoreReply storeReply){
        this.storeReply = storeReply;
    }

    @Builder
    public StoreReReply(Long storeReReplyContent, User user, StoreReply storeReply) {
        this.storeReReplyContent = storeReReplyContent;
        this.user = user;
        this.storeReply = storeReply;
    }
}

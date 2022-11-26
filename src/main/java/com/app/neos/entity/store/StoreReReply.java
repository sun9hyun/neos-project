package com.app.neos.entity.store;

import com.app.neos.domain.store.StoreReReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE_REREPLY")
@Getter @ToString(exclude = {"user", "storeReply"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreReReply extends Period {
    @Id @GeneratedValue @NonNull
    private Long storeReReplyId;
    @NonNull
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
    public StoreReReply(@NonNull Long storeReReplyContent) {
        this.storeReReplyContent = storeReReplyContent;
    }
    public void update(StoreReReplyDTO storeReReplyDTO){
        this.storeReReplyContent = storeReReplyDTO.getStoreReReplyContent();
    }
}

package com.app.neosproject.entity.store;

import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import com.app.neosproject.type.store.StoreReplySecret;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE_REPLY")
@Getter @ToString(exclude = {"user", "store"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreReply extends Period {
    @Id @GeneratedValue
    private Long storeReplyId;

    private String storeReplyContent;

    @Enumerated(EnumType.STRING)
    private StoreReplySecret storeReplySecret;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeStore(Store store){
        this.store = store;
    }
    @Builder
    public StoreReply(String storeReplyContent, StoreReplySecret storeReplySecret, User user, Store store) {
        this.storeReplyContent = storeReplyContent;
        this.storeReplySecret = storeReplySecret;
        this.user = user;
        this.store = store;
    }
}

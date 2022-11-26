package com.app.neos.entity.store;

import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.store.StoreReplySecret;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE_REPLY")
@Getter @ToString(exclude = {"user", "store"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreReply extends Period {
    @Id @GeneratedValue @NonNull
    private Long storeReplyId;
    @NonNull
    private String storeReplyContent;

    @Enumerated(EnumType.STRING) @NonNull
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
    public StoreReply(@NonNull String storeReplyContent, @NonNull StoreReplySecret storeReplySecret) {
        this.storeReplyContent = storeReplyContent;
        this.storeReplySecret = storeReplySecret;
    }

    public void update(StoreReplyDTO storeReplyDTO){
        this.storeReplyContent = storeReplyDTO.getStoreReplyContent();
    }

}

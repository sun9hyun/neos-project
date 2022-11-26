package com.app.neos.domain.store;

import com.app.neos.entity.store.Store;
import com.app.neos.entity.store.StoreReply;
import com.app.neos.entity.user.User;
import com.app.neos.type.store.StoreReplySecret;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StoreReplyDTO {
    private Long storeReplyId;
    private String storeReplyContent;
    private StoreReplySecret storeReplySecret;
    private User user;
    private Store store;

    public StoreReply toEntity(){
        return StoreReply.builder()
                .storeReplyContent(storeReplyContent)
                .storeReplySecret(storeReplySecret)
                .build();
    }
    @QueryProjection
    public StoreReplyDTO(Long storeReplyId, String storeReplyContent, StoreReplySecret storeReplySecret, User user, Store store) {
        this.storeReplyId = storeReplyId;
        this.storeReplyContent = storeReplyContent;
        this.storeReplySecret = storeReplySecret;
        this.user = user;
        this.store = store;
    }
}

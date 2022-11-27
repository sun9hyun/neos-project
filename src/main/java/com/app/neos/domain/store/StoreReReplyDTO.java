package com.app.neos.domain.store;

import com.app.neos.entity.store.StoreReReply;
import com.app.neos.entity.store.StoreReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StoreReReplyDTO {
    private Long storeReReplyId;
    private String storeReReplyContent;
    private User user;
    private StoreReply storeReply;

   public StoreReReply toEntity(){
       return StoreReReply.builder()
               .storeReReplyContent(storeReReplyContent)
               .build();
   }
    @QueryProjection
    public StoreReReplyDTO(Long storeReReplyId, String storeReReplyContent, User user, StoreReply storeReply) {
        this.storeReReplyId = storeReReplyId;
        this.storeReReplyContent = storeReReplyContent;
        this.user = user;
        this.storeReply = storeReply;
    }
}

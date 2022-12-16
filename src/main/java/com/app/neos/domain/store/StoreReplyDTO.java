package com.app.neos.domain.store;

import com.app.neos.entity.store.Store;
import com.app.neos.entity.store.StoreReply;
import com.app.neos.entity.user.User;
import com.app.neos.type.store.StoreReplySecret;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class StoreReplyDTO {
    private Long storeReplyId;
    private String storeReplyContent;
    private StoreReplySecret storeReplySecret;
    private User user;
    private Long userId;
    private Store store;
    private Long storeId;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private String userNickName;
    private String storeTitle;

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

    @QueryProjection
    public StoreReplyDTO(Long storeReplyId, String storeReplyContent, StoreReplySecret storeReplySecret, Long userId, Long storeId) {
        this.storeReplyId = storeReplyId;
        this.storeReplyContent = storeReplyContent;
        this.storeReplySecret = storeReplySecret;
        this.userId = userId;
        this.storeId = storeId;
    }

    @QueryProjection
    public StoreReplyDTO(Long storeReplyId, String storeReplyContent, StoreReplySecret storeReplySecret, User user, Long storeId, LocalDateTime updatedDate) {
        this.storeReplyId = storeReplyId;
        this.storeReplyContent = storeReplyContent;
        this.storeReplySecret = storeReplySecret;
        this.user = user;
        this.storeId = storeId;
        this.updatedDate = updatedDate;
    }


    @QueryProjection
    public StoreReplyDTO(Long storeReplyId, String storeReplyContent, String userNickName, String storeTitle, LocalDateTime createdDate) {
        this.storeReplyId = storeReplyId;
        this.storeReplyContent = storeReplyContent;
        this.userNickName = userNickName;
        this.storeTitle = storeTitle;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public StoreReplyDTO(Long storeReplyId, String storeReplyContent, String userNickName, String storeTitle, LocalDateTime createdDate, Long userId, Long storeId) {
        this.storeReplyId = storeReplyId;
        this.storeReplyContent = storeReplyContent;
        this.userNickName = userNickName;
        this.storeTitle = storeTitle;
        this.createdDate = createdDate;
        this.userId = userId;
        this.storeId = storeId;
    }





}

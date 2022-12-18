package com.app.neos.domain.store;

import com.app.neos.entity.store.Store;
import com.app.neos.entity.store.StoreFile;
import com.app.neos.entity.user.User;
import com.app.neos.type.store.StoreStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class StoreDTO {
    private Long storeId;
    private StoreStatus storeStatus;
    private Integer storePoint;
    private String storeTitle;
    private String storeContent;
    // DTO에 entity 사용X
    private User user;
    private Long userId;
    private List<StoreFlieDTO> storeFlieDTOS;

    private String userNickName;
    private LocalDateTime createdDate;

    public Store toEntity(){
        return Store.builder()
                .storeStatus(storeStatus)
                .storePoint(storePoint)
                .storeTitle(storeTitle)
                .storeContent(storeContent)
                .build();
    }


    @QueryProjection
    public StoreDTO(Long storeId, StoreStatus storeStatus, Integer storePoint, String storeTitle, String storeContent, List<StoreFlieDTO> storeFlieDTOS) {
        this.storeId = storeId;
        this.storeStatus = storeStatus;
        this.storePoint = storePoint;
        this.storeTitle = storeTitle;
        this.storeContent = storeContent;
        this.storeFlieDTOS = storeFlieDTOS;
    }

    @QueryProjection
    public StoreDTO(Long storeId, StoreStatus storeStatus, Integer storePoint, String storeTitle, String storeContent, Long userId) {
        this.storeId = storeId;
        this.storeStatus = storeStatus;
        this.storePoint = storePoint;
        this.storeTitle = storeTitle;
        this.storeContent = storeContent;
        this.userId = userId;
    }

    @QueryProjection
    public StoreDTO(Long storeId, StoreStatus storeStatus, Integer storePoint, String storeTitle, String storeContent, LocalDateTime createdDate,  User user) {
        this.storeId = storeId;
        this.storeStatus = storeStatus;
        this.storePoint = storePoint;
        this.storeTitle = storeTitle;
        this.storeContent = storeContent;
        this.createdDate = createdDate;
        this.user = user;
    }


    @QueryProjection
    public StoreDTO(Long storeId, StoreStatus storeStatus, Integer storePoint, String storeTitle, String storeContent) {
        this.storeId = storeId;
        this.storeStatus = storeStatus;
        this.storePoint = storePoint;
        this.storeTitle = storeTitle;
        this.storeContent = storeContent;
    }

    @QueryProjection
    public StoreDTO(Long storeId, StoreStatus storeStatus, Integer storePoint, String storeTitle, String storeContent, User user) {
        this.storeId = storeId;
        this.storeStatus = storeStatus;
        this.storePoint = storePoint;
        this.storeTitle = storeTitle;
        this.storeContent = storeContent;
        this.user = user;
    }

    @QueryProjection
    public StoreDTO(Long storeId, String storeTitle, String userNickName, LocalDateTime createdDate) {
        this.storeId = storeId;
        this.storeTitle = storeTitle;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public StoreDTO(Long storeId, String storeTitle, String userNickName, LocalDateTime createdDate, Long userId) {
        this.storeId = storeId;
        this.storeTitle = storeTitle;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
        this.userId = userId;
    }
}

package com.app.neos.domain.store;

import com.app.neos.entity.store.Store;
import com.app.neos.entity.store.StoreFile;
import com.app.neos.entity.user.User;
import com.app.neos.type.store.StoreStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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
//    private User user;
    private Long userId;
    private List<StoreFlieDTO> storeFlieDTOS;

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
    public StoreDTO(Long storeId, StoreStatus storeStatus, Integer storePoint, String storeTitle, String storeContent) {
        this.storeId = storeId;
        this.storeStatus = storeStatus;
        this.storePoint = storePoint;
        this.storeTitle = storeTitle;
        this.storeContent = storeContent;
    }
}

package com.app.neos.domain.store;

import com.app.neos.entity.store.Store;
import com.app.neos.entity.store.StoreFile;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StoreFlieDTO {
    private Long storeFileId;
    private String storeFileName;
    private String storeFilePath;
    private String storeFileUuid;
    private Long storeFileSize;
    private String storeFileQR;
    private Store store;
    private Long storeId;

    public StoreFile toEntity(){
        return StoreFile.builder()
                .storeFileName(storeFileName)
                .storeFilePath(storeFilePath)
                .storeFileUuid(storeFileUuid)
                .storeFileSize(storeFileSize)
                /*.storeFileQR(storeFileQR)*/
                .build();
    }

    @QueryProjection
    public StoreFlieDTO(Long storeFileId, String storeFileName, String storeFilePath, String storeFileUuid, Long storeFileSize, Store store) {
        this.storeFileId = storeFileId;
        this.storeFileName = storeFileName;
        this.storeFilePath = storeFilePath;
        this.storeFileUuid = storeFileUuid;
        this.storeFileSize = storeFileSize;
        this.store = store;
    }

    @QueryProjection
    public StoreFlieDTO(Long storeFileId, String storeFileName, String storeFilePath, String storeFileUuid, Long storeFileSize, Long storeId) {
        this.storeFileId = storeFileId;
        this.storeFileName = storeFileName;
        this.storeFilePath = storeFilePath;
        this.storeFileUuid = storeFileUuid;
        this.storeFileSize = storeFileSize;
        this.storeId = storeId;
    }

    @QueryProjection
    public StoreFlieDTO(Long storeFileId, String storeFileName, String storeFileQR, Long storeId) {
        this.storeFileId = storeFileId;
        this.storeFileName = storeFileName;
        this.storeFileQR = storeFileQR;
        this.storeId = storeId;
    }
}

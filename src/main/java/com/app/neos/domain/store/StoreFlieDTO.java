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
    private String storeFileQR;
    private Store store;

    public StoreFile toEntity(){
        return StoreFile.builder()
                .storeFileName(storeFileName)
                .storeFilePath(storeFilePath)
                .storeFileQR(storeFileQR)
                .build();
    }

    @QueryProjection
    public StoreFlieDTO(Long storeFileId, String storeFileName, String storeFilePath, String storeFileQR, Store store) {
        this.storeFileId = storeFileId;
        this.storeFileName = storeFileName;
        this.storeFilePath = storeFilePath;
        this.storeFileQR = storeFileQR;
        this.store = store;
    }
}

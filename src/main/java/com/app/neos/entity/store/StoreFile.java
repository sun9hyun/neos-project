package com.app.neos.entity.store;

import com.app.neos.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE_FILE")
@Getter @ToString(exclude = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreFile extends Period {
    @Id @GeneratedValue
    private Long storeFileId;
    @NotNull
    private String storeFileName;
    @NotNull
    private String storeFilePath;
    @NotNull
    private String storeFileQR;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public void changeStore(Store store){
        this.store = store;
        store.getFiles().add(this);
    }

    @Builder
    public StoreFile(@NotNull String storeFileName, @NotNull String storeFilePath, @NotNull String storeFileQR) {
        this.storeFileName = storeFileName;
        this.storeFilePath = storeFilePath;
        this.storeFileQR = storeFileQR;
    }
}

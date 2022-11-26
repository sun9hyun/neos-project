package com.app.neos.entity.store;

import com.app.neos.entity.period.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE_FILE")
@Getter @ToString(exclude = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreFile extends Period {
    @Id @GeneratedValue @NonNull
    private Long storeFileId;
    @NonNull
    private String storeFileName;
    @NonNull
    private String storeFilePath;
    @NonNull
    private String storeFileQR;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public void changeStore(Store store){
        this.store = store;
        store.getFiles().add(this);
    }

    @Builder
    public StoreFile(@NonNull String storeFileName, @NonNull String storeFilePath, @NonNull String storeFileQR) {
        this.storeFileName = storeFileName;
        this.storeFilePath = storeFilePath;
        this.storeFileQR = storeFileQR;
    }
}

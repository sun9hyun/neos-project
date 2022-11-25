package com.app.neosproject.entity.store;

import com.app.neosproject.entity.period.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE_FILE")
@Getter @ToString(exclude = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreFile extends Period {
    @Id @GeneratedValue
    private Long storeFileId;

    private String storeFileName;
    private String storeFilePath;
    private String storeFileQR;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public void changeStore(Store store){
        this.store = store;
    }

    @Builder
    public StoreFile(String storeFileName, String storeFilePath, String storeFileQR, Store store) {
        this.storeFileName = storeFileName;
        this.storeFilePath = storeFilePath;
        this.storeFileQR = storeFileQR;
        this.store = store;
    }
}

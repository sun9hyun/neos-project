package com.app.neos.entity.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.store.StoreStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_STORE")
@Getter @ToString(exclude = {"user","files"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends Period {

    @Id @GeneratedValue @NonNull
    private Long storeId;

    @Enumerated(EnumType.STRING) @NonNull
    private StoreStatus storeStatus;
    @NonNull
    private Integer storePoint;
    @NonNull
    private String storeTitle;
    @NonNull
    private String storeContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "store")
    List<StoreFile> files;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeFiles(List<StoreFile> files){
        this.files = files;
    }

    @Builder
    public Store(@NonNull StoreStatus storeStatus, @NonNull Integer storePoint, @NonNull String storeTitle, @NonNull String storeContent) {
        this.storeStatus = storeStatus;
        this.storePoint = storePoint;
        this.storeTitle = storeTitle;
        this.storeContent = storeContent;
    }

    public void update(StoreDTO storeDTO){
        this.storeStatus = storeDTO.getStoreStatus();
        this.storePoint = storeDTO.getStorePoint();
        this.storeTitle = storeDTO.getStoreTitle();
        this.storeContent = storeDTO.getStoreContent();
    }

}

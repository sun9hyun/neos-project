package com.app.neos.entity.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.store.StoreStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_STORE")
@Getter @ToString(exclude = {"user","files"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends Period {

    @Id @GeneratedValue
    private Long storeId;

    @Enumerated(EnumType.STRING) @NotNull
    private StoreStatus storeStatus;
    @NotNull
    private Integer storePoint;
    @NotNull
    private String storeTitle;
    @NotNull
    private String storeContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "store")
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<StoreFile> files;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeFiles(List<StoreFile> files){
        this.files = files;
    }

    @Builder
    public Store(@NotNull StoreStatus storeStatus, @NotNull Integer storePoint, @NotNull String storeTitle, @NotNull String storeContent) {
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

    public StoreDTO toDTO() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreTitle(this.storeTitle);
        storeDTO.setStoreContent(this.storeContent);
        storeDTO.setStoreStatus(this.storeStatus);
        return storeDTO;
    }
}

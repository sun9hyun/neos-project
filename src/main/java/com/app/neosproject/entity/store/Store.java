package com.app.neosproject.entity.store;

import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import com.app.neosproject.type.store.StoreStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends Period {

    @Id @GeneratedValue
    private Long storeId;

    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;
    private Integer storePoint;
    private String storeTitle;
    private String storeContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public Store(StoreStatus storeStatus, Integer storePoint, String storeTitle, String storeContent, User user) {
        this.storeStatus = storeStatus;
        this.storePoint = storePoint;
        this.storeTitle = storeTitle;
        this.storeContent = storeContent;
        this.user = user;
    }
}

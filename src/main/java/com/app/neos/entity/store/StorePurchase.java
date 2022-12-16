package com.app.neos.entity.store;

import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE_PURCHASE")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StorePurchase extends Created {
    @Id @GeneratedValue
    private Long storePurchaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store store;

    public void changeUser(User user){
        this.user = user;
    }

    public void changeStore(Store store) { this.store = store;}

    public static StorePurchase create() {return new StorePurchase();}
}

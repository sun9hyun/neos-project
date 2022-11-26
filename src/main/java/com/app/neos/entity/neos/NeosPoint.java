package com.app.neos.entity.neos;

import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.point.NeosPointContent;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name="TBL_NEOS_POINT")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NeosPoint extends Created {
    @Id
    @GeneratedValue @NonNull
    private Long neosPointId;

    @NonNull
    private Integer neosPointMoney;

    @Enumerated(EnumType.STRING)
    @NonNull
    private NeosPointContent neosPointContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public NeosPoint(@NonNull Integer neosPointMoney, @NonNull NeosPointContent neosPointContent) {
        this.neosPointMoney = neosPointMoney;
        this.neosPointContent = neosPointContent;
    }
}

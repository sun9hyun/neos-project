package com.app.neos.entity.neos;

import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.point.NeosPointContent;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name="TBL_NEOS_POINT")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NeosPoint extends Created {
    @Id
    @GeneratedValue
    private Long neosPointId;

    @NotNull
    private Integer neosPointMoney;

    @Enumerated(EnumType.STRING)
    @NotNull
    private NeosPointContent neosPointContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public NeosPoint(@NotNull Integer neosPointMoney, @NotNull NeosPointContent neosPointContent) {
        this.neosPointMoney = neosPointMoney;
        this.neosPointContent = neosPointContent;
    }
}

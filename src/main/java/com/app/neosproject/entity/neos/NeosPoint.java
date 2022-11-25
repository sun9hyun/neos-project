package com.app.neosproject.entity.neos;

import com.app.neosproject.entity.period.Created;
import com.app.neosproject.entity.user.User;
import com.app.neosproject.type.point.NeosPointContent;
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

    private Integer neosPointMoney;
    private NeosPointContent neosPointContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public NeosPoint(Integer neosPointMoney, NeosPointContent neosPointContent, User user) {
        this.neosPointMoney = neosPointMoney;
        this.neosPointContent = neosPointContent;
        this.user = user;
    }
}

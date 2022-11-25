package com.app.neosproject.entity.neos;


import com.app.neosproject.entity.period.Created;
import com.app.neosproject.entity.user.User;
import com.app.neosproject.type.point.NeosPowerContent;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="TBL_NEOS_POWER")
@Getter @ToString(exclude = "user")
public class NeosPower extends Created {
    @Id @GeneratedValue
    private Long neosPowerId;

    private Integer neosPowerAbility;

    @Enumerated(EnumType.STRING)
    private NeosPowerContent neosPowerContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public NeosPower(Integer neosPowerAbility, NeosPowerContent neosPowerContent, User user) {
        this.neosPowerAbility = neosPowerAbility;
        this.neosPowerContent = neosPowerContent;
        this.user = user;
    }
}


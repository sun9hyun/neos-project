package com.app.neos.entity.neos;


import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.point.NeosPowerContent;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_NEOS_POWER")
@Getter @ToString(exclude = "user")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class NeosPower extends Created {
    @Id @GeneratedValue
    private Long neosPowerId;
    @NotNull
    private Integer neosPowerAbility;

    @Enumerated(EnumType.STRING) @NotNull
    private NeosPowerContent neosPowerContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public NeosPower(@NotNull Integer neosPowerAbility, @NotNull NeosPowerContent neosPowerContent) {
        this.neosPowerAbility = neosPowerAbility;
        this.neosPowerContent = neosPowerContent;
    }
}


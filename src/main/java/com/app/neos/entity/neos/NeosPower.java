package com.app.neos.entity.neos;


import com.app.neos.entity.period.Created;
import com.app.neos.entity.user.User;
import com.app.neos.type.point.NeosPowerContent;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="TBL_NEOS_POWER")
@Getter @ToString(exclude = "user")
public class NeosPower extends Created {
    @Id @GeneratedValue @NonNull
    private Long neosPowerId;
    @NonNull
    private Integer neosPowerAbility;

    @Enumerated(EnumType.STRING) @NonNull
    private NeosPowerContent neosPowerContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public NeosPower(@NonNull Integer neosPowerAbility, @NonNull NeosPowerContent neosPowerContent) {
        this.neosPowerAbility = neosPowerAbility;
        this.neosPowerContent = neosPowerContent;
    }
}


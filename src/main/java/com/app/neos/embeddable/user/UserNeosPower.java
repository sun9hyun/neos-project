package com.app.neos.embeddable.user;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNeosPower {
    @NonNull
    private String userNeosBadge;
    @NonNull
    private Integer userNeosPowerLevel;
    @NonNull
    private Integer userNeosPowerAbility;

    @Builder
    public UserNeosPower(@NonNull String userNeosBadge, @NonNull Integer userNeosPowerLevel, @NonNull Integer userNeosPowerAbility) {
        this.userNeosBadge = userNeosBadge;
        this.userNeosPowerLevel = userNeosPowerLevel;
        this.userNeosPowerAbility = userNeosPowerAbility;
    }
}

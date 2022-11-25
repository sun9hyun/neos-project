package com.app.neosproject.embeddable.user;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNeosPower {
   private String userNeosBadge;
   private Integer userNeosPowerLevel;
   private Integer userNeosPowerAbility;

   @Builder
    public UserNeosPower(String userNeosBadge, Integer userNeosPowerLevel, Integer userNeosPowerAbility) {
        this.userNeosBadge = userNeosBadge;
        this.userNeosPowerLevel = userNeosPowerLevel;
        this.userNeosPowerAbility = userNeosPowerAbility;
    }
}

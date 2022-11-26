package com.app.neos.embeddable.user;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLike {
    private String userO2o;
    private String userCity;
    private String userDay;
    private String userTime;


    @Builder
    public UserLike(String userO2o, String userCity, String userDay, String userTime) {
        this.userO2o = userO2o;
        this.userCity = userCity;
        this.userDay = userDay;
        this.userTime = userTime;
    }
}


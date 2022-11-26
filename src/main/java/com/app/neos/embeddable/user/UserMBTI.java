package com.app.neos.embeddable.user;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMBTI {
    private String userMbtiName;
    private String userMbtiColor;


    @Builder
    public UserMBTI(String userMbtiName, String userMbtiColor) {
        this.userMbtiName = userMbtiName;
        this.userMbtiColor = userMbtiColor;
    }
}

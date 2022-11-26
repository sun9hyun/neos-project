package com.app.neos.embeddable.user;

import com.app.neos.type.user.UserCollegeMajor;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCollegeInfo {

    private Integer userCollegeYear;

    @Enumerated(EnumType.STRING)
    private UserCollegeMajor userCollegeMajor;

    @Builder
    public UserCollegeInfo(Integer userCollegeYear, UserCollegeMajor userCollegeMajor) {
        this.userCollegeYear = userCollegeYear;
        this.userCollegeMajor = userCollegeMajor;
    }
}

package com.app.neosproject.embeddable.user;

import com.app.neosproject.type.user.UserCollegeMajor;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCollegeInfo {
    private boolean userCollegeCertify;
    private Integer userCollegeYear;
    private UserCollegeMajor userCollegeMajor;

    @Builder
    public UserCollegeInfo(boolean userCollegeCertify, Integer userCollegeYear, UserCollegeMajor userCollegeMajor) {
        this.userCollegeCertify = userCollegeCertify;
        this.userCollegeYear = userCollegeYear;
        this.userCollegeMajor = userCollegeMajor;
    }
}

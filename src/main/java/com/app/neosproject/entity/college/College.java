package com.app.neosproject.entity.college;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_COLLEGE")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class College {
    @Id @GeneratedValue
    private Long collegeId;
    private String collegeCity;
    private String collegeName;
    private String collegeLogoFile;
    private String collegeEmailDomain;

    @Builder
    public College(String collegeCity, String collegeName, String collegeLogoFile, String collegeEmailDomain) {
        this.collegeCity = collegeCity;
        this.collegeName = collegeName;
        this.collegeLogoFile = collegeLogoFile;
        this.collegeEmailDomain = collegeEmailDomain;
    }
}

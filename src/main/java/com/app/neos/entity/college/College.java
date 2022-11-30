package com.app.neos.entity.college;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_COLLEGE")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class College extends Period{
    @Id @GeneratedValue
    private Long collegeId;
    @NotNull
    private String collegeCity;
    @NotNull
    private String collegeName;
    @NotNull
    private String collegeLogoFile;
    @NotNull
    private String collegeEmailDomain;


    @Builder
    public College(String collegeCity, String collegeName, String collegeLogoFile, String collegeEmailDomain, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.collegeCity = collegeCity;
        this.collegeName = collegeName;
        this.collegeLogoFile = collegeLogoFile;
        this.collegeEmailDomain = collegeEmailDomain;

    }

    public void update(CollegeDTO collegeDTO){
        this.collegeCity = collegeDTO.getCollegeCity();
        this.collegeName = collegeDTO.getCollegeName();
        this.collegeLogoFile = collegeDTO.getCollegeLogoFile();
        this.collegeEmailDomain = collegeDTO.getCollegeEmailDomain();
    }
}

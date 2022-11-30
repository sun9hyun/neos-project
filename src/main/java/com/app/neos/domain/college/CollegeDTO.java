package com.app.neos.domain.college;

import com.app.neos.entity.college.College;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor

public class CollegeDTO {
    private Long collegeId;
    private String collegeCity;
    private String collegeName;
    private String collegeLogoFile;
    private String collegeEmailDomain;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public College toEntity(){
        return College.builder()
                .collegeCity(collegeCity)
                .collegeName(collegeName)
                .collegeLogoFile(collegeLogoFile)
                .collegeEmailDomain(collegeEmailDomain)
                .build();
    }

    @QueryProjection
    public CollegeDTO(Long collegeId, String collegeCity, String collegeName, String collegeLogoFile, String collegeEmailDomain) {
        this.collegeId = collegeId;
        this.collegeCity = collegeCity;
        this.collegeName = collegeName;
        this.collegeLogoFile = collegeLogoFile;
        this.collegeEmailDomain = collegeEmailDomain;
    }

    @QueryProjection
    public CollegeDTO(Long collegeId, String collegeCity, String collegeName, String collegeLogoFile, String collegeEmailDomain, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.collegeId = collegeId;
        this.collegeCity = collegeCity;
        this.collegeName = collegeName;
        this.collegeLogoFile = collegeLogoFile;
        this.collegeEmailDomain = collegeEmailDomain;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

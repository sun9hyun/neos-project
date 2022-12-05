package com.app.neos.domain.study;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StudySearch {
    private String studyCity;
    private String collegeName;
    private String studyType;

    public StudySearch(String studyCity, String collegeName, String studyType) {
        this.studyCity = studyCity;
        this.collegeName = collegeName;
        this.studyType = studyType;
    }
}

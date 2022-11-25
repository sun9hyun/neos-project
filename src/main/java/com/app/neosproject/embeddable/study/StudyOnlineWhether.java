package com.app.neosproject.embeddable.study;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyOnlineWhether {
    private String studyO2o;
    private String studyCity;

    @Builder
    public StudyOnlineWhether(String studyO2o, String studyCity) {
        this.studyO2o = studyO2o;
        this.studyCity = studyCity;
    }
}

package com.app.neos.embeddable.study;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyOnlineWhether {
    @NonNull
    private String studyO2o;
    @NonNull
    private String studyCity;

    @Builder
    public StudyOnlineWhether(@NonNull String studyO2o, @NonNull String studyCity) {
        this.studyO2o = studyO2o;
        this.studyCity = studyCity;
    }
}

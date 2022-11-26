package com.app.neos.embeddable.study;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyField {
    @NonNull
    private String studyType;
    @NonNull
    private String studyKeyword;

    @Builder
    public StudyField(@NonNull String studyType, @NonNull String studyKeyword) {
        this.studyType = studyType;
        this.studyKeyword = studyKeyword;
    }
}

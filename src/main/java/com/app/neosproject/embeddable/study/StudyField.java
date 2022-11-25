package com.app.neosproject.embeddable.study;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyField {
    private String studyType;
    private String studyKeyword;

    @Builder
    public StudyField(String studyType, String studyKeyword) {
        this.studyType = studyType;
        this.studyKeyword = studyKeyword;
    }
}

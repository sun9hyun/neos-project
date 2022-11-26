package com.app.neos.domain.study;

import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyFeed;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StudyFeedDTO {
    private Long studyFeedId;
    private String studyFeedContent;

    private User studyFeedWriter;
    private Study study;


    public StudyFeed toEntity(){
        return StudyFeed.builder()
                .studyFeedContent(studyFeedContent)
                .build();
    }

    @QueryProjection
    public StudyFeedDTO(Long studyFeedId, String studyFeedContent, User studyFeedWriter, Study study) {
        this.studyFeedId = studyFeedId;
        this.studyFeedContent = studyFeedContent;
        this.studyFeedWriter = studyFeedWriter;
        this.study = study;
    }
}

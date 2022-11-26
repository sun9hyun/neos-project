package com.app.neos.domain.study;

import com.app.neos.entity.study.StudyFeed;
import com.app.neos.entity.study.StudyFeedReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StudyFeedReplyDTO {
    private Long studyFeedReplyId;
    private String studyFeedReplyContent;
    private User studyFeedReplyWriter;
    private StudyFeed studyFeed;

    public StudyFeedReply toEntity(){
        return StudyFeedReply.builder()
                .studyFeedReplyContent(studyFeedReplyContent)
                .build();
    }

    @QueryProjection
    public StudyFeedReplyDTO(Long studyFeedReplyId, String studyFeedReplyContent, User studyFeedReplyWriter, StudyFeed studyFeed) {
        this.studyFeedReplyId = studyFeedReplyId;
        this.studyFeedReplyContent = studyFeedReplyContent;
        this.studyFeedReplyWriter = studyFeedReplyWriter;
        this.studyFeed = studyFeed;
    }
}

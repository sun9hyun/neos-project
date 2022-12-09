package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="TBL_STUDY_FEED_REPLY")
@Getter @ToString(exclude = {"studyFeedReplyWriter", "studyFeed"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyFeedReply extends Period {
    @Id @GeneratedValue
    private Long studyFeedReplyId;
    @NotNull
    private String studyFeedReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User studyFeedReplyWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STUDY_FEED_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StudyFeed studyFeed;

    public void changeStudyFeedReplyWriter(User studyFeedReplyWriter){
        this.studyFeedReplyWriter =studyFeedReplyWriter;
    }
    public void changeStudyFeed(StudyFeed studyFeed){
        this.studyFeed = studyFeed;
    }

    @Builder
    public StudyFeedReply(@NotNull String studyFeedReplyContent) {
        this.studyFeedReplyContent = studyFeedReplyContent;
    }

    public void update(StudyFeedReplyDTO studyFeedReplyDTO){
        this.studyFeedReplyContent = studyFeedReplyDTO.getStudyFeedReplyContent();
    }
}

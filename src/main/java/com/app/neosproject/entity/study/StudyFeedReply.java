package com.app.neosproject.entity.study;

import com.app.neosproject.entity.period.Period;
import com.app.neosproject.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_STUDY_FEED_REPLY")
@Getter @ToString(exclude = {"studyFeedReplyWriter", "studyFeed"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyFeedReply extends Period {
    @Id @GeneratedValue
    private Long studyFeedReplyId;
    private String studyFeedReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User studyFeedReplyWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STUDY_FEED_ID")
    private StudyFeed studyFeed;

    public void changeStudyFeedReplyWriter(User studyFeedReplyWriter){
        this.studyFeedReplyWriter =studyFeedReplyWriter;
    }
    public void changeStudyFeed(StudyFeed studyFeed){
        this.studyFeed = studyFeed;
    }

    @Builder
    public StudyFeedReply(String studyFeedReplyContent, User studyFeedReplyWriter, StudyFeed studyFeed) {
        this.studyFeedReplyContent = studyFeedReplyContent;
        this.studyFeedReplyWriter = studyFeedReplyWriter;
        this.studyFeed = studyFeed;
    }
}

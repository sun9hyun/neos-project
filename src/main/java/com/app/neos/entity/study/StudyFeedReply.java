package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
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
        studyFeed.getReplies().add(this);
    }

    @Builder
    public StudyFeedReply(@NotNull String studyFeedReplyContent) {
        this.studyFeedReplyContent = studyFeedReplyContent;
    }

    public void update(StudyFeedReplyDTO studyFeedReplyDTO){
        this.studyFeedReplyContent = studyFeedReplyDTO.getStudyFeedReplyContent();
    }

    public StudyFeedReplyDTO toDTO(){
        String dateFormat = this.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        StudyFeedReplyDTO dto = new StudyFeedReplyDTO();
        dto.setStudyFeedReplyId(this.studyFeedReplyId);
        dto.setStudyFeedReplyContent(this.studyFeedReplyContent);
        dto.setStudyFeed(this.studyFeed.toDTO());
        dto.setStudyFeedReplyWriter(this.studyFeedReplyWriter.toDTO());
        dto.setCreatedDate(dateFormat);
        return dto;
    }
}

package com.app.neos.domain.study;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.StudyFeed;
import com.app.neos.entity.study.StudyFeedReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class StudyFeedReplyDTO {
    private Long studyFeedReplyId;
    private String studyFeedReplyContent;
    private UserDTO studyFeedReplyWriter;
    private StudyFeedDTO studyFeed;
    private String createdDate;

    private String userNickName;
    private LocalDateTime createdDate2;
    private String studyTitle;

    private Long userId;
    private Long studyId;

    public StudyFeedReply toEntity(){
        return StudyFeedReply.builder()
                .studyFeedReplyContent(studyFeedReplyContent)
                .build();
    }

    @QueryProjection
    public StudyFeedReplyDTO(Long studyFeedReplyId, String studyFeedReplyContent, UserDTO studyFeedReplyWriter, StudyFeedDTO studyFeed) {
        this.studyFeedReplyId = studyFeedReplyId;
        this.studyFeedReplyContent = studyFeedReplyContent;
        this.studyFeedReplyWriter = studyFeedReplyWriter;
        this.studyFeed = studyFeed;
    }

    @QueryProjection
    public StudyFeedReplyDTO(Long studyFeedReplyId, String studyFeedReplyContent, String userNickName, LocalDateTime createdDate2, String studyTitle) {
        this.studyFeedReplyId = studyFeedReplyId;
        this.studyFeedReplyContent = studyFeedReplyContent;
        this.userNickName = userNickName;
        this.createdDate2 = createdDate2;
        this.studyTitle = studyTitle;
    }

    @QueryProjection
    public StudyFeedReplyDTO(Long studyFeedReplyId, String studyFeedReplyContent, String userNickName, LocalDateTime createdDate2, String studyTitle, Long userId, Long studyId) {
        this.studyFeedReplyId = studyFeedReplyId;
        this.studyFeedReplyContent = studyFeedReplyContent;
        this.userNickName = userNickName;
        this.createdDate2 = createdDate2;
        this.studyTitle = studyTitle;
        this.userId = userId;
        this.studyId = studyId;
    }
}

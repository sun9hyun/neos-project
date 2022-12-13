package com.app.neos.domain.counseling;

import com.app.neos.entity.counseling.Counseling;
import com.app.neos.entity.counseling.CounselingReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class CounselingReplyDTO {

    private Long counselingReplyId;
    private String counselingReplyContent;
    private User user;
    private Counseling counseling;
    private Long userId;
    private Long counselingId;

    private String userNickName;
    private String counselingTitle;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public CounselingReply toEntity(){
        return CounselingReply.builder()
                .counselingReplyContent(counselingReplyContent)
                .build();
    }

    @QueryProjection
    public CounselingReplyDTO(Long counselingReplyId, String counselingReplyContent, User user, Counseling counseling, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.counselingReplyId = counselingReplyId;
        this.counselingReplyContent = counselingReplyContent;
        this.user = user;
        this.counseling = counseling;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @QueryProjection
    public CounselingReplyDTO(Long counselingReplyId, String counselingReplyContent, Long userId, Long counselingId) {
        this.counselingReplyId = counselingReplyId;
        this.counselingReplyContent = counselingReplyContent;
        this.userId = userId;
        this.counselingId = counselingId;
    }

    @QueryProjection
    public CounselingReplyDTO(Long counselingReplyId, String counselingReplyContent, String userNickName, String counselingTitle, LocalDateTime createdDate) {
        this.counselingReplyId = counselingReplyId;
        this.counselingReplyContent = counselingReplyContent;
        this.userNickName = userNickName;
        this.counselingTitle = counselingTitle;
        this.createdDate = createdDate;
    }
}

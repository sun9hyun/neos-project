package com.app.neos.domain.counseling;

import com.app.neos.entity.counseling.CounselingReReply;
import com.app.neos.entity.counseling.CounselingReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CounselingReReplyDTO {

    private Long counselingReReplyId;
    private String counselingReReplyContent;
    private User user;
    private CounselingReply counselingReply;

    public CounselingReReply toEntity(){
        return CounselingReReply.builder()
                .counselingReReplyContent(counselingReReplyContent)
                .build();
    }

    @QueryProjection
    public CounselingReReplyDTO(Long counselingReReplyId, String counselingReReplyContent, User user, CounselingReply counselingReply) {
        this.counselingReReplyId = counselingReReplyId;
        this.counselingReReplyContent = counselingReReplyContent;
        this.user = user;
        this.counselingReply = counselingReply;
    }
}

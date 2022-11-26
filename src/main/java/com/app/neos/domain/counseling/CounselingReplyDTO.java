package com.app.neos.domain.counseling;

import com.app.neos.entity.counseling.Counseling;
import com.app.neos.entity.counseling.CounselingReply;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CounselingReplyDTO {

    private Long counselingReplyId;
    private String counselingReplyContent;
    private User user;
    private Counseling counseling;

    public CounselingReply toEntity(){
        return CounselingReply.builder()
                .counselingReplyContent(counselingReplyContent)
                .build();
    }

    @QueryProjection
    public CounselingReplyDTO(Long counselingReplyId, String counselingReplyContent, User user, Counseling counseling) {
        this.counselingReplyId = counselingReplyId;
        this.counselingReplyContent = counselingReplyContent;
        this.user = user;
        this.counseling = counseling;
    }
}

package com.app.neos.domain.notice;

import com.app.neos.entity.notice.Notice;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class NoticeDTO {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Notice toEntity(){
        return Notice.builder()
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .build();
    }

    @QueryProjection
    public NoticeDTO(Long noticeId, String noticeTitle, String noticeContent) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }


    @QueryProjection
    public NoticeDTO(Long noticeId, String noticeTitle, String noticeContent, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }




}

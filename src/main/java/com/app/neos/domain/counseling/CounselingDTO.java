package com.app.neos.domain.counseling;

import com.app.neos.entity.counseling.Counseling;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class CounselingDTO {

    private Long counselingId;
    private String counselingTitle;
    private String counselingContent;
    private User user;
    private Long userId;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private String userNickName;

    public Counseling toEntity(){
        return Counseling.builder()
                .counselingTitle(counselingTitle)
                .counselingContent(counselingContent)
                .build();
    }

    @QueryProjection
    public CounselingDTO(Long counselingId, String counselingTitle, String counselingContent, User user, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.counselingId = counselingId;
        this.counselingTitle = counselingTitle;
        this.counselingContent = counselingContent;
        this.user = user;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @QueryProjection
    public CounselingDTO(Long counselingId, String counselingTitle, String counselingContent, Long userId) {
        this.counselingId = counselingId;
        this.counselingTitle = counselingTitle;
        this.counselingContent = counselingContent;
        this.userId = userId;
    }

    @QueryProjection
    public CounselingDTO(Long counselingId, String counselingTitle, String userNickName, LocalDateTime createdDate) {
        this.counselingId = counselingId;
        this.counselingTitle = counselingTitle;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public CounselingDTO(Long counselingId, String counselingTitle, String userNickName, LocalDateTime createdDate, Long userId) {
        this.counselingId = counselingId;
        this.counselingTitle = counselingTitle;
        this.userNickName = userNickName;
        this.createdDate = createdDate;
        this.userId = userId;
    }

}

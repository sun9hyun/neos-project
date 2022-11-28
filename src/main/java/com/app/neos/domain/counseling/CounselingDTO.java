package com.app.neos.domain.counseling;

import com.app.neos.entity.counseling.Counseling;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CounselingDTO {

    private Long counselingId;
    private String counselingTitle;
    private String counselingContent;
    private User user;

    public Counseling toEntity(){
        return Counseling.builder()
                .counselingTitle(counselingTitle)
                .counselingContent(counselingContent)
                .build();
    }

    @QueryProjection
    public CounselingDTO(Long counselingId, String counselingTitle, String counselingContent, User user) {
        this.counselingId = counselingId;
        this.counselingTitle = counselingTitle;
        this.counselingContent = counselingContent;
        this.user = user;
    }
}

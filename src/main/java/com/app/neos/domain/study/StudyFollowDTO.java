package com.app.neos.domain.study;

import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StudyFollowDTO {
    private Long studyFollowId;
    private Study study;
    private User user;

    @QueryProjection
    public StudyFollowDTO(Long studyFollowId, Study study, User user) {
        this.studyFollowId = studyFollowId;
        this.study = study;
        this.user = user;
    }
}

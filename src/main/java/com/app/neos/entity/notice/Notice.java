package com.app.neos.entity.notice;

import com.app.neos.entity.period.Period;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_NOTICE")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Period {
    @Id @GeneratedValue @NonNull
    private Long noticeId;
    @NonNull
    private String noticeTitle;
    @NonNull
    private String noticeContent;

    @Builder
    public Notice(@NonNull String noticeTitle, @NonNull String noticeContent) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }
}

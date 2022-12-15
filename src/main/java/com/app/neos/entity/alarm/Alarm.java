package com.app.neos.entity.alarm;

import com.app.neos.domain.Alarm.AlarmDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.alarm.AlarmCategory;
import com.app.neos.type.alarm.ReadStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ALARM")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends Period {
    @Id
    @GeneratedValue
    private Long alarmId; //PK


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user; //FK

    @NotNull
    private String alarmContent;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReadStatus readStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AlarmCategory alarmCategory;

    private Long contentId;

    private String url;

    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public Alarm(String alarmContent, ReadStatus readStatus, AlarmCategory alarmCategory, Long contentId, String url) {
        this.alarmContent = alarmContent;
        this.readStatus = readStatus;
        this.alarmCategory = alarmCategory;
        this.contentId = contentId;
        this.url = url;
    }

    public void updateStatus(){
        this.readStatus = ReadStatus.READ;
    }

    public AlarmDTO toDTO(){
        AlarmDTO dto = new AlarmDTO();
        dto.setAlarmId(alarmId);
        dto.setAlarmCategory(alarmCategory);
        dto.setAlarmContent(alarmContent);
        dto.setContentId(contentId);
        dto.setReadStatus(readStatus);
        dto.setUser(user.toDTO());
        dto.setCreatedTime(this.getCreatedDate());
        dto.setUpdatedTime(this.getUpdatedDate());
        dto.setUrl(url);
        return dto;
    }
}

package com.app.neos.domain.Alarm;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.alarm.Alarm;
import com.app.neos.type.alarm.AlarmCategory;
import com.app.neos.type.alarm.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class AlarmDTO {
    private Long alarmId;
    private UserDTO user;
    private String alarmContent;
    private ReadStatus readStatus;
    private AlarmCategory alarmCategory;
    private Long contentId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public Alarm toEntity(){
        return Alarm.builder().alarmCategory(alarmCategory).contentId(contentId).alarmContent(alarmContent).readStatus(readStatus).build();
    }

    @QueryProjection
    public AlarmDTO(Long alarmId, String alarmContent, ReadStatus readStatus, AlarmCategory alarmCategory, Long contentId) {
        this.alarmId = alarmId;
        this.alarmContent = alarmContent;
        this.readStatus = readStatus;
        this.alarmCategory = alarmCategory;
        this.contentId = contentId;
    }




}

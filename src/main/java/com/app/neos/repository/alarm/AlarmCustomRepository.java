package com.app.neos.repository.alarm;

import com.app.neos.entity.alarm.Alarm;

import java.util.List;

public interface AlarmCustomRepository {

    public List<Alarm> findAllNoReadAlarmByUserId(Long userId);

    public List<Alarm> findAllAlarmByUserId(Long userId);

    public List<Alarm> findByUserIdNoRead(Long userId);
}

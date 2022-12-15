package com.app.neos.repository.alarm;


import com.app.neos.entity.alarm.Alarm;
import com.app.neos.entity.alarm.QAlarm;
import com.app.neos.type.alarm.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.alarm.QAlarm.*;

@Repository
@RequiredArgsConstructor
public class AlarmCustomRepositoryImpl implements AlarmCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Alarm> findAllNoReadAlarmByUserId(Long userId) {
        return jpaQueryFactory.selectFrom(alarm).where(alarm.user.userId.eq(userId).and(alarm.readStatus.eq(ReadStatus.NO))).orderBy(alarm.createdDate.desc()).fetch();
    }

    @Override
    public List<Alarm> findAllAlarmByUserId(Long userId) {
       return jpaQueryFactory.selectFrom(alarm).where(alarm.user.userId.eq(userId)).orderBy(alarm.createdDate.desc()).fetch();
    }




}

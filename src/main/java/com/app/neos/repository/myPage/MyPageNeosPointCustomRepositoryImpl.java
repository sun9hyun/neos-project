package com.app.neos.repository.myPage;

import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.domain.neos.QNeosPointDTO;
import com.app.neos.domain.neos.QNeosPowerDTO;
import com.app.neos.entity.neos.QNeosPoint;
import com.app.neos.entity.neos.QNeosPower;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageNeosPointCustomRepositoryImpl implements MyPageNeosPointCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NeosPointDTO> neosPointList(Long userId) {
        return jpaQueryFactory.select(new QNeosPointDTO(
                QNeosPoint.neosPoint.neosPointId,
                QNeosPoint.neosPoint.neosPointMoney,
                QNeosPoint.neosPoint.neosPointContent,
                QNeosPoint.neosPoint.user.userId,
                QNeosPoint.neosPoint.createdDate
                ))
                .from(QNeosPoint.neosPoint)
                .where(QNeosPoint.neosPoint.user.userId.eq(userId))
                .limit(30)
                .orderBy(QNeosPoint.neosPoint.neosPointId.desc())
                .fetch();
    }
}

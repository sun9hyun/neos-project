package com.app.neos.repository.myPage;

import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.domain.neos.QNeosPowerDTO;
import com.app.neos.entity.neos.QNeosPower;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageNeosPowerCustomRepositoryImpl implements MyPageNeosPowerCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NeosPowerDTO> neosPowerList(Long userId) {
        return jpaQueryFactory.select(new QNeosPowerDTO(
                QNeosPower.neosPower.neosPowerId,
                QNeosPower.neosPower.neosPowerAbility,
                QNeosPower.neosPower.neosPowerContent,
                QNeosPower.neosPower.user.userId,
                QNeosPower.neosPower.createdDate
                ))
                .from(QNeosPower.neosPower)
                .where(QNeosPower.neosPower.user.userId.eq(userId))
                .limit(30)
                .orderBy(QNeosPower.neosPower.neosPowerId.desc())
                .fetch();
    }
}

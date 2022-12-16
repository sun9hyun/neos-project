package com.app.neos.repository.store;

import com.app.neos.domain.study.QStudySupporterDTO;
import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.entity.store.QStorePurchase;
import com.app.neos.entity.store.StorePurchase;
import com.app.neos.entity.study.QStudySupporter;
import com.app.neos.repository.myPage.MyPageSupporterCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StorePurchaseCustomRepositoryImpl implements StorePurchaseCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public boolean duplicate(Long userId, Long storeId) {
        List<StorePurchase> storePurchases = jpaQueryFactory.selectFrom(QStorePurchase.storePurchase)
                .where(QStorePurchase.storePurchase.user.userId.eq(userId).and(QStorePurchase.storePurchase.store.storeId.eq(storeId)))
                .fetch();

//        List<StorePurchase> storePurchases = jpaQueryFactory.select(new QStorePurchaseDTO(
//                QStorePurchase.storePurchase.storePurchaseId,
//                QStorePurchase.storePurchase.user.userId,
//                QStorePurchase.storePurchase.store.storeId
//        ))
//                .from(QStorePurchase.storePurchase)
//                .where(QStorePurchase.storePurchase.user.userId.eq(userId).and(QStorePurchase.storePurchase.store.storeId.eq(storeId)))
//                .fetch();
        System.out.println("********************* storePurchases.size() : " + storePurchases.size() + "*********************");


        return storePurchases.size() != 0;
    }

    @Override
    public StorePurchase findByIdAndStoreId(Long userId, Long storeId) {
        return jpaQueryFactory.selectFrom(QStorePurchase.storePurchase)
                .where(QStorePurchase.storePurchase.user.userId.eq(userId).and(QStorePurchase.storePurchase.store.storeId.eq(storeId)))
                .fetchOne();
    }
}

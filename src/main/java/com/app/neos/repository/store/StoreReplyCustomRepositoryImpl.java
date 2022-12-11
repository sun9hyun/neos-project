package com.app.neos.repository.store;

import com.app.neos.domain.store.QStoreReplyDTO;
import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.entity.store.QStoreReply;
import com.app.neos.entity.store.StoreReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreReplyCustomRepositoryImpl implements StoreReplyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<StoreReplyDTO> findAllReply(Long storeId) {
        return jpaQueryFactory.select(new QStoreReplyDTO(
                QStoreReply.storeReply.storeReplyId,
                QStoreReply.storeReply.storeReplyContent,
                QStoreReply.storeReply.storeReplySecret,
                QStoreReply.storeReply.user,
                QStoreReply.storeReply.store.storeId,
                QStoreReply.storeReply.updatedDate
                ))
                .from(QStoreReply.storeReply)
                .where(QStoreReply.storeReply.store.storeId.eq(storeId))
                .orderBy(QStoreReply.storeReply.updatedDate.asc())
                .fetch();
    }

    @Override
    public StoreReplyDTO findOneReply(Long storeReplyId) {
        return jpaQueryFactory.select(new QStoreReplyDTO(
                QStoreReply.storeReply.storeReplyId,
                QStoreReply.storeReply.storeReplyContent,
                QStoreReply.storeReply.storeReplySecret,
                QStoreReply.storeReply.user.userId,
                QStoreReply.storeReply.store.storeId
        ))
                .from(QStoreReply.storeReply)
                .where(QStoreReply.storeReply.storeReplyId.eq(storeReplyId))
                .fetchOne();
    }

    @Override
    public void update(StoreReply storeReply) {
        System.out.println("*********************" + storeReply + "*********************");

        jpaQueryFactory.update(QStoreReply.storeReply)
                .set(QStoreReply.storeReply.storeReplyContent, storeReply.getStoreReplyContent())
                .where(QStoreReply.storeReply.storeReplyId.eq(storeReply.getStoreReplyId()))
                .execute();
    }

    @Override
    public int findReplyCount(Long storeId) {
        return jpaQueryFactory.select(new QStoreReplyDTO(
                QStoreReply.storeReply.storeReplyId,
                QStoreReply.storeReply.storeReplyContent,
                QStoreReply.storeReply.storeReplySecret,
                QStoreReply.storeReply.user,
                QStoreReply.storeReply.store
                ))
                .from(QStoreReply.storeReply)
                .where(QStoreReply.storeReply.store.storeId.eq(storeId))
                .fetch().size();
    }

    @Override
    public void deleteByStoreId(Long storeId) {
        jpaQueryFactory.delete(QStoreReply.storeReply)
                .where(QStoreReply.storeReply.store.storeId.eq(storeId))
                .execute();
    }
}

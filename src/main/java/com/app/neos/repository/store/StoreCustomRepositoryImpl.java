package com.app.neos.repository.store;

import com.app.neos.domain.store.QStoreDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.QStore;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.user.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreCustomRepositoryImpl implements StoreCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    // 자료상점 게시글 수정
    @Override
    public void update(Store store) {

        System.out.println("*********************" + store + "*********************");

        jpaQueryFactory.update(QStore.store)
                .set(QStore.store.storeContent,store.getStoreContent())
                .set(QStore.store.storeTitle,store.getStoreTitle())
                .set(QStore.store.storeStatus,store.getStoreStatus())
                .set(QStore.store.storePoint,store.getStorePoint())
                .where(QStore.store.storeId.eq(store.getStoreId()))
                .execute();
    }

    // 자료상점 게시글 삭제
    @Override
    public void delete(Long storeId) {
        jpaQueryFactory.delete(QStore.store)
                .where(QStore.store.storeId.eq(storeId))
                .execute();
    }

    // 자료상점 게시글 조회
    @Override
    public StoreDTO selectOne(Long storeId) {
        return jpaQueryFactory.select(new QStoreDTO(
                QStore.store.storeId,
                QStore.store.storeStatus,
                QStore.store.storePoint,
                QStore.store.storeTitle,
                QStore.store.storeContent,
                QStore.store.user
                ))
                .from(QStore.store)
                .where(QStore.store.storeId.eq(storeId))
                .fetchOne();
    }

    @Override
    public List<StoreDTO> findAll() {
        return jpaQueryFactory.select(new QStoreDTO(
                QStore.store.storeId,
                QStore.store.storeStatus,
                QStore.store.storePoint,
                QStore.store.storeTitle,
                QStore.store.storeContent
                ))
                .from(QStore.store)
                .orderBy(QStore.store.storeId.desc())
                .fetch();
    }

    @Override
    public Page<StoreDTO> findAllPage(Pageable pageable) {

        List<StoreDTO> storeDTOS = jpaQueryFactory.select(new QStoreDTO(
                QStore.store.storeId,
                QStore.store.storeStatus,
                QStore.store.storePoint,
                QStore.store.storeTitle,
                QStore.store.storeContent
                ))
                .from(QStore.store)
                .orderBy(QStore.store.storeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QStoreDTO(
                QStore.store.storeId,
                QStore.store.storeStatus,
                QStore.store.storePoint,
                QStore.store.storeTitle,
                QStore.store.storeContent
                ))
                .from(QStore.store).fetch().size();

        return new PageImpl<>(storeDTOS,pageable,total);
    }
}

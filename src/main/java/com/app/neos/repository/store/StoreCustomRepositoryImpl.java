package com.app.neos.repository.store;

import com.app.neos.domain.store.QStoreDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.QStore;
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

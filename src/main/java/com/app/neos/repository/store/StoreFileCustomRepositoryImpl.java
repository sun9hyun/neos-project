package com.app.neos.repository.store;

import com.app.neos.domain.store.QStoreFlieDTO;
import com.app.neos.domain.store.StoreFlieDTO;
import com.app.neos.entity.store.QStoreFile;
import com.app.neos.entity.store.StoreFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreFileCustomRepositoryImpl implements StoreFileCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<StoreFlieDTO> findByStoreId(Long storeId) {

        return jpaQueryFactory.select(new QStoreFlieDTO(
                QStoreFile.storeFile.storeFileId,
                QStoreFile.storeFile.storeFileName,
                QStoreFile.storeFile.storeFilePath,
                QStoreFile.storeFile.storeFileUuid,
                QStoreFile.storeFile.storeFileSize,
                QStoreFile.storeFile.store.storeId
                ))
                .from(QStoreFile.storeFile)
                .where(QStoreFile.storeFile.store.storeId.eq(storeId))
                .fetch();
    }

    @Override
    public void deleteByStoreId(Long storeId) {
        jpaQueryFactory.delete(QStoreFile.storeFile)
                .where(QStoreFile.storeFile.store.storeId.eq(storeId))
                .execute();
    }
}

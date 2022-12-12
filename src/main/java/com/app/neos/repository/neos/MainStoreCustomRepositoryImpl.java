package com.app.neos.repository.neos;

import com.app.neos.domain.store.QStoreDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.store.QStore;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MainStoreCustomRepositoryImpl  implements MainStoreCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<StoreDTO> findAllPage(Pageable pageable) {

        List<StoreDTO> storeDTOS = jpaQueryFactory.select(new QStoreDTO(

                QStore.store.storeId,
                QStore.store.storeStatus,
                QStore.store.storePoint,
                QStore.store.storeTitle,
                QStore.store.storeContent,
                QStore.store.user.userId


        ))
                .from(QStore.store)
                .orderBy(QStore.store.updatedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        ArrayList<StoreDTO> content = (ArrayList<StoreDTO>)storeDTOS;

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext=true;
        }
        return new SliceImpl<>(content , pageable , hasNext);
    }
}

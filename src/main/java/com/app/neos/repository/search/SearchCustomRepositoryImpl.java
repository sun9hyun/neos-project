package com.app.neos.repository.search;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.store.QStore;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.study.Study;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.neos.entity.store.QStore.store;
import static com.app.neos.entity.study.QStudy.study;

@RequiredArgsConstructor
@Repository
public class SearchCustomRepositoryImpl implements SearchCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<StudyDTO> findByKeywordStudy(String keyword, Pageable pageable) {
        List<Study> studyList = jpaQueryFactory
                .selectFrom(study)
                .where(study.studyTitle.like("%" + keyword+ "%").or(study.studyField.studyKeyword.like("%" + keyword+ "%")))
                .orderBy(study.studyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        List<StudyDTO> studyDTOS = studyList.stream().map(Study::toDTO).collect(Collectors.toList());

        ArrayList<StudyDTO> content = (ArrayList<StudyDTO>)studyDTOS;

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext = false;
        }

        return new SliceImpl<>(content , pageable , hasNext);
    }

    @Override
    public Slice<StoreDTO> findByKeywordStore(String keyword, Pageable pageable) {
        List<Store> storeList = jpaQueryFactory
                .selectFrom(store)
                .where(store.storeTitle.like("%" + keyword+ "%").or(store.storeContent.like("%" + keyword+ "%")))
                .orderBy(store.storeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        List<StoreDTO> storeDTOS = storeList.stream().map(Store::toDTO).collect(Collectors.toList());

        ArrayList<StoreDTO> content = (ArrayList<StoreDTO>)storeDTOS;

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext = false;
        }

        return new SliceImpl<>(content , pageable , hasNext);

    }

    @Override
    public Slice<StudyDTO> findByStudyList(Pageable pageable) {
        return null;
    }
}

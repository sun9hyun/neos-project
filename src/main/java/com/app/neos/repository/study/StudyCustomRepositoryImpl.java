package com.app.neos.repository.study;


import com.app.neos.entity.study.QStudy;
import com.app.neos.entity.study.Study;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.study.QStudy.*;

@Repository
@RequiredArgsConstructor
public class StudyCustomRepositoryImpl implements StudyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Study> findAllPage(Pageable pageable) {
        List<Study> studyList = jpaQueryFactory.selectFrom(study)
                .orderBy(study.studyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = jpaQueryFactory.selectFrom(study).fetch().size();
        return new PageImpl<>(studyList,pageable,total);
    }

    @Override
    public int getTotal() {
        return jpaQueryFactory.selectFrom(study).fetch().size();
    }
}

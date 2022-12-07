package com.app.neos.repository.study;


import com.app.neos.domain.study.StudySearch;
import com.app.neos.entity.study.QStudy;
import com.app.neos.entity.study.Study;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.neos.entity.study.QStudy.*;
import static com.app.neos.entity.user.QUser.user;

@Repository
@RequiredArgsConstructor
public class StudyCustomRepositoryImpl implements StudyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Study> findAllPage(Pageable pageable, StudySearch search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanBuilder booleanBuilder2 = new BooleanBuilder();

        if(search.getCollegeName() != null){
            booleanBuilder.and(study.user.college.collegeName.eq(search.getCollegeName()));
        }
        if(search.getStudyCity() != null){
            booleanBuilder2.and(study.studyOnlineWhether.studyCity.eq(search.getStudyCity()));
        }
        if(search.getStudyType() != null){
            booleanBuilder.and(study.studyField.studyType.eq(search.getStudyType()));
        }



        List<Study> studyList = jpaQueryFactory.selectFrom(study)
                .where(booleanBuilder.and(booleanBuilder2))
                .orderBy(study.studyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(study).where(booleanBuilder).fetch().size();
        return new PageImpl<>(studyList,pageable,total);
    }

    @Override
    public List<Study> findUntilFour() {
        return jpaQueryFactory.selectFrom(study).orderBy(study.studyId.desc()).limit(4l).fetch();
    }

    @Override
    public long pageTotal(Pageable pageable, StudySearch search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanBuilder booleanBuilder2 = new BooleanBuilder();

        if(search.getCollegeName() != null){
            booleanBuilder.and(study.user.college.collegeName.eq(search.getCollegeName()));
        }
        if(search.getStudyCity() != null){
            booleanBuilder2.and(study.studyOnlineWhether.studyCity.eq(search.getStudyCity()));
        }
        if(search.getStudyType() != null){
            booleanBuilder.and(study.studyField.studyType.eq(search.getStudyType()));
        }

        return jpaQueryFactory.selectFrom(study).where(booleanBuilder.and(booleanBuilder2)).fetch().size();
    }

    @Override
    public int getTotal() {
        return jpaQueryFactory.selectFrom(study).fetch().size();
    }

    @Override
    public Slice<Study> findAllSlice(Pageable pageable) {
        List<Study> studyList = jpaQueryFactory.selectFrom(study)
                .orderBy(study.studyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        ArrayList<Study> content = (ArrayList<Study>)studyList.stream().collect(Collectors.toList());

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<Study>(content,pageable,hasNext);
    }
}

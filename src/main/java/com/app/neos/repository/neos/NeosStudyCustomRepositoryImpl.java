package com.app.neos.repository.neos;

import com.app.neos.domain.study.QStudyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.study.Study;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.app.neos.entity.study.QStudy.study;


@RequiredArgsConstructor
@Repository
public class NeosStudyCustomRepositoryImpl implements NeosStudyCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Study> findStudyForMain() {
        return jpaQueryFactory.selectFrom(study).orderBy(study.createdDate.desc()).limit(4l).fetch();
    }


}

package com.app.neos.repository.study;

import com.app.neos.entity.study.QStudyFollow;
import com.app.neos.entity.study.StudyFollow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.study.QStudyFollow.*;

@Repository
@RequiredArgsConstructor
public class StudyFollowCustomRepositoryImpl implements StudyFollowCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public boolean duplicate(Long userId, Long studyId) {
        List<StudyFollow> list = jpaQueryFactory.selectFrom(studyFollow)
                .where(studyFollow.user.userId.eq(userId).and(studyFollow.study.studyId.eq(studyId)))
                .fetch();
        return list.size() != 0 ;
    }

    @Override
    public StudyFollow findByIdAndStudyId(Long userId, Long studyId) {
        return jpaQueryFactory.selectFrom(studyFollow).
                where(studyFollow.user.userId.eq(userId).and(studyFollow.study.studyId.eq(studyId))).fetchOne();
    }
}

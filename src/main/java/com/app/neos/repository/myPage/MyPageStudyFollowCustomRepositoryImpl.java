package com.app.neos.repository.myPage;

import com.app.neos.domain.study.QStudyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.study.QStudy;
import com.app.neos.entity.study.QStudyFollow;
import com.app.neos.entity.study.StudyFollow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageStudyFollowCustomRepositoryImpl implements MyPageStudyFollowCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<StudyFollow> findByUserId(Long userId) {
        return jpaQueryFactory.selectFrom(QStudyFollow.studyFollow)
                .where(QStudyFollow.studyFollow.user.userId.eq(userId)).fetch();

//        return null;
    }


//    @Override
//    public List<StudyFollow> findByUserId(Long userId) {
//        List<StudyFollow> studyFollows = jpaQueryFactory.selectFrom(QStudyFollow.studyFollow)
//                .where(QStudyFollow.studyFollow.user.userId.eq(userId)).fetch();
//
//
//
//        List<StudyDTO> studyDTOS = new ArrayList<>();
//
//        for (StudyFollow studyFollow: studyFollows) {
//            Long studyId = studyFollow.getStudy().getStudyId();
//            StudyDTO studyDTO = adminReop.findByStudyId(studyID);
//
//            studyDTOS.add(studyDTO);
//        }
//
//
//        return null;
//    }
}

package com.app.neos.repository.myPage;

import com.app.neos.domain.study.QStudyMemberDTO;
import com.app.neos.domain.study.QStudySupporterDTO;
import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.domain.study.StudySupporterDTO;
import com.app.neos.entity.study.QStudyMember;
import com.app.neos.entity.study.QStudySupporter;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageSupporterCustomRepositoryImpl implements MyPageSupporterCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<StudySupporterDTO> findByUserId(Long userId) {
        return jpaQueryFactory.select(new QStudySupporterDTO(
                QStudySupporter.studySupporter.studySupporterId,
                QStudySupporter.studySupporter.studySupporterStatus,
                QStudySupporter.studySupporter.user.userId,
                QStudySupporter.studySupporter.study.studyId
                ))
                .from(QStudySupporter.studySupporter)
                .where(QStudySupporter.studySupporter.user.userId.eq(userId))
                .fetch();
    }
}

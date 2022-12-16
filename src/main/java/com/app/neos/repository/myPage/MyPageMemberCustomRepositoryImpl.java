package com.app.neos.repository.myPage;

import com.app.neos.domain.study.QStudyMemberDTO;
import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.entity.study.QStudyMember;
import com.app.neos.entity.user.QUser;
import com.app.neos.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageMemberCustomRepositoryImpl implements MyPageMemberCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<StudyMemberDTO> findByUserId(Long userId) {
        return jpaQueryFactory.select(new QStudyMemberDTO(
                QStudyMember.studyMember.studyMemberId,
                QStudyMember.studyMember.studyMemberStatus,
                QStudyMember.studyMember.user.userId,
                QStudyMember.studyMember.study.studyId
                ))
                .from(QStudyMember.studyMember)
                .where(QStudyMember.studyMember.user.userId.eq(userId))
                .fetch();
    }
}

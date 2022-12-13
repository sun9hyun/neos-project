package com.app.neos.repository.counseling;

import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.domain.counseling.QCounselingDTO;
import com.app.neos.domain.counseling.QCounselingReplyDTO;
import com.app.neos.entity.community.QCommunityReply;
import com.app.neos.entity.counseling.QCounseling;
import com.app.neos.entity.counseling.QCounselingReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CounselingReplyCustomRepositoryImpl implements CounselingReplyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<CounselingReplyDTO> findAll(Long counselingId) {
        return jpaQueryFactory.select(new QCounselingReplyDTO(
                QCounselingReply.counselingReply.counselingReplyId,
                QCounselingReply.counselingReply.counselingReplyContent,
                QCounselingReply.counselingReply.user,
                QCounselingReply.counselingReply.counseling,
                QCounselingReply.counselingReply.createdDate,
                QCounselingReply.counselingReply.updatedDate
        ))
                .from(QCounselingReply.counselingReply)
                .where(QCounselingReply.counselingReply.counseling.counselingId.eq(counselingId))
                .orderBy(QCounselingReply.counselingReply.updatedDate.asc())
                .fetch();
    }
}

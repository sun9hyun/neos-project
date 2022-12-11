package com.app.neos.repository.community;

import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.domain.community.QCommunityReplyDTO;
import com.app.neos.entity.community.QCommunityReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommunityReplyCustomRepositoryImpl implements CommunityReplyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommunityReplyDTO> findAll(Long communityId) {
        return jpaQueryFactory.select(new QCommunityReplyDTO(
                QCommunityReply.communityReply.communityReplyId,
                QCommunityReply.communityReply.communityReplyContent,
                QCommunityReply.communityReply.communityReplyLikeCount,
                QCommunityReply.communityReply.user,
                QCommunityReply.communityReply.community,
                QCommunityReply.communityReply.createdDate,
                QCommunityReply.communityReply.updatedDate
        ))
                .from(QCommunityReply.communityReply)
                .where(QCommunityReply.communityReply.community.communityId.eq(communityId))
                .orderBy(QCommunityReply.communityReply.createdDate.desc())
                .fetch();
    }
}

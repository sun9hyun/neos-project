package com.app.neos.repository.community;

import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.domain.community.QCommunityReplyDTO;
import com.app.neos.entity.community.QCommunityReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.neos.entity.community.QCommunityReply.*;

@Repository
@RequiredArgsConstructor
public class CommunityReplyCustomRepositoryImpl implements CommunityReplyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommunityReplyDTO> findAll(Long communityId) {
        return jpaQueryFactory.select(new QCommunityReplyDTO(
                communityReply.communityReplyId,
                communityReply.communityReplyContent,
                communityReply.communityReplyLikeCount,
                communityReply.user,
                communityReply.community,
                communityReply.createdDate,
                communityReply.updatedDate
        ))
                .from(communityReply)
                .where(communityReply.community.communityId.eq(communityId))
                .orderBy(communityReply.updatedDate.asc())
                .fetch();
    }
}

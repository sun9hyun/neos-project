package com.app.neos.repository.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.QCommunityDTO;
import com.app.neos.entity.community.QCommunity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommunityCustomRepositoryImpl implements CommunityCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommunityDTO> findAll() {
        return jpaQueryFactory.select(new QCommunityDTO(
                QCommunity.community.communityId,
                QCommunity.community.communityTitle,
                QCommunity.community.communityContent,
                QCommunity.community.communityLikeCount,
                QCommunity.community.user
                ))
                .from(QCommunity.community)
                .orderBy(QCommunity.community.communityId.desc())
                .fetch();

    }

    @Override
    public CommunityDTO findByCommunityId(Long communityId) {
        return jpaQueryFactory.select(new QCommunityDTO(
                QCommunity.community.communityId,
                QCommunity.community.communityTitle,
                QCommunity.community.communityContent,
                QCommunity.community.communityLikeCount,
                QCommunity.community.user
        ))
                .from(QCommunity.community)
                .where(QCommunity.community.communityId.eq(communityId))
                .fetchOne();
    }


}

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
                QCommunity.community.user,
                QCommunity.community.createdDate,
                QCommunity.community.updatedDate
                ))
                .from(QCommunity.community)
                .orderBy(QCommunity.community.updatedDate.desc())
                .fetch();

    }

    @Override
    public CommunityDTO findByCommunityId(Long communityId) {
        return jpaQueryFactory.select(new QCommunityDTO(
                QCommunity.community.communityId,
                QCommunity.community.communityTitle,
                QCommunity.community.communityContent,
                QCommunity.community.communityLikeCount,
                QCommunity.community.user,
                QCommunity.community.createdDate,
                QCommunity.community.updatedDate
        ))
                .from(QCommunity.community)
                .where(QCommunity.community.communityId.eq(communityId))
                .fetchOne();
    }

//    @Override
//    public CommunityDTO update(Long communityId) {
//        return jpaQueryFactory.update(new QCommunityDTO(
//                QCommunity.community.communityTitle,
//                QCommunity.community.communityContent
//        ))
//                .from(QCommunity.community)
//                .where(QCommunity.community.communityId.eq(communityId))
//                ;
//    }


}

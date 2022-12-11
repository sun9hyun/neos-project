package com.app.neos.repository.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.CommunityLikeDTO;
import com.app.neos.domain.community.QCommunityDTO;
import com.app.neos.domain.community.QCommunityLikeDTO;
import com.app.neos.entity.community.CommunityLike;
import com.app.neos.entity.community.QCommunity;
import com.app.neos.entity.community.QCommunityLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Slice<CommunityDTO> findAllPage(Pageable pageable) {
        List<CommunityDTO> communityDTOList = jpaQueryFactory.select(new QCommunityDTO(
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        ArrayList<CommunityDTO> content = (ArrayList<CommunityDTO>)communityDTOList;

        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext=true;
        }
        return new SliceImpl<>(content,pageable,hasNext);
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

    @Override
    public List<CommunityLikeDTO> findByLikeId(Long communityId) {
        return jpaQueryFactory.select(new QCommunityLikeDTO(
                QCommunityLike.communityLike.communityLikeId,
                QCommunityLike.communityLike.user,
                QCommunityLike.communityLike.community
        ))
                .from(QCommunityLike.communityLike)
                .where(QCommunityLike.communityLike.community.communityId.eq(communityId))
                .fetch();
    }

    @Override
    public List<CommunityLikeDTO> findLike(Long communityId, Long userId) {
        return jpaQueryFactory.select(new QCommunityLikeDTO(
                QCommunityLike.communityLike.communityLikeId,
                QCommunityLike.communityLike.user,
                QCommunityLike.communityLike.community
        ))
                .from(QCommunityLike.communityLike)
                .where(QCommunityLike.communityLike.community.communityId.eq(communityId))
                .where(QCommunityLike.communityLike.user.userId.eq(userId))
                .fetch();
    }

    @Override
    public List<CommunityLikeDTO> findByCommunityAndUser(Long communityId, Long userId) {
        return jpaQueryFactory.select(new QCommunityLikeDTO(
                QCommunityLike.communityLike.communityLikeId,
                QCommunityLike.communityLike.user,
                QCommunityLike.communityLike.community
        ))
                .from(QCommunityLike.communityLike)
                .join(QCommunityLike.communityLike.community)
                .fetchJoin()
                .fetch();
    }
//    List<Pet> fetchJoin = jpaQueryFactory.selectFrom(pet).join(pet.owner).fetchJoin().fetch();
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

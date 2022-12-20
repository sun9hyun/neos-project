package com.app.neos.repository.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.QCommunityDTO;
import com.app.neos.domain.study.QStudyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.community.QCommunityLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.app.neos.entity.community.QCommunity.community;
import static com.app.neos.entity.study.QStudy.study;
import static com.app.neos.entity.user.QUser.user;

@Repository
@RequiredArgsConstructor
public class CommunityCustomRepositoryImpl implements CommunityCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommunityDTO> findAll() {
        return jpaQueryFactory.select(new QCommunityDTO(
                community.communityId,
                community.communityTitle,
                community.communityContent,
                community.communityLikeCount,
                community.user,
                community.createdDate,
                community.updatedDate
                ))
                .from(community)
                .orderBy(community.updatedDate.desc())
                .fetch();

    }

    @Override
    public List<UserDTO> findNeosUser() {
        return jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userNickName,
                user.userFile,
                user.createdDate
        ))
                .from(user)
//                .orderBy(NumberExpression.random().asc())
                .limit(5)
                .fetch();
    }

    @Override
    public List<StudyDTO> findStudy() {
        return jpaQueryFactory.select(new QStudyDTO(
                study.studyId,
                study.studyView,
                study.studyTitle,
                study.studyField.studyType,
                study.user.userNickName,
                study.createdDate
        ))
                .from(study)
                .orderBy(study.studyView.desc())
                .limit(5)
                .fetch();
    }


    @Override
    public Slice<CommunityDTO> findAllPageS(Pageable pageable) {
        List<CommunityDTO> communityDTOList = jpaQueryFactory.select(new QCommunityDTO(
                community.communityId,
                community.communityTitle,
                community.communityContent,
                community.communityLikeCount,
                community.user,
                community.createdDate,
                community.updatedDate
                ))
                .from(community)
                .orderBy(community.updatedDate.desc())
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
    public List<CommunityDTO> findAllPage(Pageable pageable) {
        List<CommunityDTO> communityDTOList = jpaQueryFactory.select(new QCommunityDTO(
                community.communityId,
                community.communityTitle,
                community.communityContent,
                community.communityLikeCount,
                community.user,
                community.createdDate,
                community.updatedDate
        ))
                .from(community)
                .orderBy(community.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        return communityDTOList;
    }

    @Override
    public CommunityDTO findByCommunityId(Long communityId) {
        return jpaQueryFactory.select(new QCommunityDTO(
                community.communityId,
                community.communityTitle,
                community.communityContent,
                community.communityLikeCount,
                community.user,
                community.createdDate,
                community.updatedDate
        ))
                .from(community)
                .where(community.communityId.eq(communityId))
                .fetchOne();
    }

    @Override
    public List<CommunityDTO> findPK() {
        List<CommunityDTO> communityDTOList = jpaQueryFactory.select(new QCommunityDTO(
                community.communityId,
                community.communityTitle,
                community.communityContent,
                community.communityLikeCount,
                community.user,
                community.createdDate,
                community.updatedDate
        ))
                .from(community)
                .fetch();
        return communityDTOList;
    }

//    @Override
//    public List<CommunityLikeDTO> findByLikeId(Long communityId) {
//        return jpaQueryFactory.select(new QCommunityLikeDTO(
//                QCommunityLike.communityLike.communityLikeId,
//                QCommunityLike.communityLike.user,
//                QCommunityLike.communityLike.community
//        ))
//                .from(QCommunityLike.communityLike)
//                .where(QCommunityLike.communityLike.community.communityId.eq(communityId))
//                .fetch();
//    }
//
//    @Override
//    public List<CommunityLikeDTO> findLike(Long communityId, Long userId) {
//        return jpaQueryFactory.select(new QCommunityLikeDTO(
//                QCommunityLike.communityLike.communityLikeId,
//                QCommunityLike.communityLike.user,
//                QCommunityLike.communityLike.community
//        ))
//                .from(QCommunityLike.communityLike)
//                .where(QCommunityLike.communityLike.community.communityId.eq(communityId))
//                .where(QCommunityLike.communityLike.user.userId.eq(userId))
//                .fetch();
//    }
//
//    @Override
//    public List<CommunityLikeDTO> findByCommunityAndUser(Long communityId, Long userId) {
//        return jpaQueryFactory.select(new QCommunityLikeDTO(
//                QCommunityLike.communityLike.communityLikeId,
//                QCommunityLike.communityLike.user,
//                QCommunityLike.communityLike.community
//        ))
//                .from(QCommunityLike.communityLike)
//                .join(QCommunityLike.communityLike.community)
//                .fetchJoin()
//                .fetch();
//    }
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

package com.app.neos.service.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.QCommunityDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.community.QCommunity;
import com.app.neos.entity.counseling.Counseling;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.counseling.CounselingService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CommunityServiceTest {
    @Autowired
    CommunityService communityService;

    @Autowired
    CounselingService counselingService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    public void test(){
        userRepository.findAll().stream().map(User::toString).forEach(log::info);
    }

    @Test
    public void saveCommuTest(){
        User user = userRepository.findById(1l).get();
        user.getUserId();

        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setCommunityTitle("3 자유게시판");
        communityDTO.setCommunityContent("3 자유 게시판 내용");
        communityDTO.setCommunityLikeCount(1);
        communityDTO.setUser(user);

        Community community = communityDTO.toEntity();
        community.changeUser(communityDTO.getUser());

        communityService.saveCommunity(community);
    }

    @Test
    public void saveCounTest(){
        User user = userRepository.findById(1l).get();
        user.getUserId();

        CounselingDTO counselingDTO = new CounselingDTO();
        counselingDTO.setCounselingTitle("2 고민 게시글");
        counselingDTO.setCounselingContent("2 고민 게시글 내용");
        counselingDTO.setUser(user);

        Counseling counseling = counselingDTO.toEntity();
        counseling.changeUser(counselingDTO.getUser());

        counselingService.saveCounseling(counseling);
    }

    @Test
    public void findAll(){
        List<CommunityDTO> communityDTOS = jpaQueryFactory.select(new QCommunityDTO(
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

}

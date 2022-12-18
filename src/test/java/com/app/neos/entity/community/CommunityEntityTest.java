package com.app.neos.entity.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.entity.user.User;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CommunityEntityTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Test
    public void saveTest(){
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setCommunityTitle("자유 게시판");
        communityDTO.setCommunityContent("자유 게시판 내용");
        communityDTO.setCommunityLikeCount(0);

        communityDTO.setUser(userRepository.findById(1L).get());

        Community community = communityDTO.toEntity();
        community.changeUser(communityDTO.getUser());

        communityRepository.save(community);

    }

    @Test
    public void updateTest(){
        Community community = communityRepository.findById(3L).get();
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setCommunityTitle("자유게시판 수정");
        communityDTO.setCommunityContent("수정");
        communityDTO.setCommunityLikeCount(1);
        community.update(communityDTO);
    }

    @Test
    public void updateLikeTest(){
        Community community = communityRepository.findById(3L).get();
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setCommunityLikeCount(6);
//        community.updateCommunityLikeCount(communityDTO);
    }

    @Test
    public void deleteTest(){
        communityRepository.deleteById(3L);
    }

    @Test
    public void findAllTest(){
        communityRepository.findAll().stream().map(Community::toString).forEach(log::info);
    }
}

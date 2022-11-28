package com.app.neos.entity.community;

import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.repository.community.CommunityReplyRepository;
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
public class CommunityReplyEntityTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    CommunityReplyRepository communityReplyRepository;

    @Test
    public void saveTest(){
        CommunityReplyDTO communityReplyDTO = new CommunityReplyDTO();
        communityReplyDTO.setCommunityReplyContent("자유 게시판 댓글");
        communityReplyDTO.setCommunityReplyLikeCount(0);

        communityReplyDTO.setUser(userRepository.findById(2L).get());
        communityReplyDTO.setCommunity(communityRepository.findById(3L).get());

        CommunityReply communityReply = communityReplyDTO.toEntity();
        communityReply.changeUser(communityReplyDTO.getUser());
        communityReply.changeCommunity(communityReplyDTO.getCommunity());

        communityReplyRepository.save(communityReply);

   }

   @Test
   public void updateTest(){
        CommunityReply communityReply = communityReplyRepository.findById(4L).get();
        CommunityReplyDTO communityReplyDTO = new CommunityReplyDTO();
        communityReplyDTO.setCommunityReplyContent("자유 게시판 댓글 수정");
        communityReplyDTO.setCommunityReplyLikeCount(2);
        communityReply.update(communityReplyDTO);
   }

   @Test
   public void updateLikeTest(){
       CommunityReply communityReply = communityReplyRepository.findById(4L).get();
       CommunityReplyDTO communityReplyDTO = new CommunityReplyDTO();
       communityReplyDTO.setCommunityReplyLikeCount(6);
       communityReply.updateCommunityLikeCount(communityReplyDTO);

   }

   @Test
    public void deleteTest(){
        communityReplyRepository.deleteById(4L);
   }

   @Test
    public void findAllTest(){
        communityReplyRepository.findAll().stream().map(CommunityReply::toString).forEach(log::info);
   }

}

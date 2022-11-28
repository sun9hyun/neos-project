package com.app.neos.entity.community;

import com.app.neos.domain.community.CommunityReReplyDTO;
import com.app.neos.repository.community.CommunityReReplyRepository;
import com.app.neos.repository.community.CommunityReplyRepository;
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
public class CommunityReReplyEntityTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommunityReplyRepository communityReplyRepository;

    @Autowired
    CommunityReReplyRepository communityReReplyRepository;

    @Test
    public void saveTest(){
        CommunityReReplyDTO communityReReplyDTO = new CommunityReReplyDTO();
        communityReReplyDTO.setReReplyContent("자유 게시판 대댓글");

        communityReReplyDTO.setUser(userRepository.findById(2L).get());
        communityReReplyDTO.setCommunityReply(communityReplyRepository.findById(4L).get());

        CommunityReReply communityReReply = communityReReplyDTO.toEntity();
        communityReReply.changeUser(communityReReplyDTO.getUser());
        communityReReply.changeCommunityReply(communityReReplyDTO.getCommunityReply());

        communityReReplyRepository.save(communityReReply);

    }

    @Test
    public void updateTest(){
        CommunityReReply communityReReply = communityReReplyRepository.findById(5L).get();
        CommunityReReplyDTO communityReReplyDTO = new CommunityReReplyDTO();
        communityReReplyDTO.setReReplyContent("대댓글 수정");
        communityReReply.update(communityReReplyDTO);

    }

    @Test
    public void deleteTest(){
        communityReReplyRepository.deleteById(5L);
    }
}

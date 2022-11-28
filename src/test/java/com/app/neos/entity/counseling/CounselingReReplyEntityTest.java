package com.app.neos.entity.counseling;

import com.app.neos.domain.counseling.CounselingReReplyDTO;
import com.app.neos.entity.community.CommunityReply;
import com.app.neos.repository.counseling.CounselingReReplyRepository;
import com.app.neos.repository.counseling.CounselingReplyRepository;
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
public class CounselingReReplyEntityTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CounselingReplyRepository counselingReplyRepository;

    @Autowired
    CounselingReReplyRepository counselingReReplyRepository;

    @Test
    public void saveTest(){
        CounselingReReplyDTO counselingReReplyDTO = new CounselingReReplyDTO();
        counselingReReplyDTO.setCounselingReReplyContent("고민 게시판 대댓글");

        counselingReReplyDTO.setUser(userRepository.findById(2L).get());
        counselingReReplyDTO.setCounselingReply(counselingReplyRepository.findById(4L).get());

        CounselingReReply counselingReReply = counselingReReplyDTO.toEntity();
        counselingReReply.changeUser(counselingReReplyDTO.getUser());
        counselingReReply.changeCounselingReply(counselingReReplyDTO.getCounselingReply());

        counselingReReplyRepository.save(counselingReReply);
    }

    @Test
    public void updateTest(){
        CounselingReReply counselingReReply = counselingReReplyRepository.findById(6L).get();
        CounselingReReplyDTO counselingReReplyDTO = new CounselingReReplyDTO();
        counselingReReplyDTO.setCounselingReReplyContent("고민 게시판 대댓글 수정");
        counselingReReply.update(counselingReReplyDTO);
    }

    @Test
    public void deleteTest(){
        counselingReReplyRepository.deleteById(6L);
    }

    @Test
    public void findAllTest(){
        counselingReReplyRepository.findAll().stream().map(CounselingReReply::toString).forEach(log::info);
    }
}

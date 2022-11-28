package com.app.neos.entity.counseling;

import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.entity.community.CommunityReply;
import com.app.neos.repository.counseling.CounselingReplyRepository;
import com.app.neos.repository.counseling.CounselingRepository;
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
public class CounselingReplyEntityTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CounselingRepository counselingRepository;

    @Autowired
    CounselingReplyRepository counselingReplyRepository;

    @Test
    public void saveTest(){
        CounselingReplyDTO counselingReplyDTO = new CounselingReplyDTO();
        counselingReplyDTO.setCounselingReplyContent("고민 게시판 댓글");

        counselingReplyDTO.setUser(userRepository.findById(2L).get());
        counselingReplyDTO.setCounseling(counselingRepository.findById(3L).get());

        CounselingReply counselingReply = counselingReplyDTO.toEntity();
        counselingReply.changeUser(counselingReplyDTO.getUser());
        counselingReply.changeCounseling(counselingReplyDTO.getCounseling());

        counselingReplyRepository.save(counselingReply);
    }

    @Test
    public void updateTest(){
        CounselingReply counselingReply = counselingReplyRepository.findById(4L).get();
        CounselingReplyDTO counselingReplyDTO = new CounselingReplyDTO();
        counselingReplyDTO.setCounselingReplyContent("고민 게시판 댓글 수정");
        counselingReply.update(counselingReplyDTO);
    }

    @Test
    public void deleteTest(){
        counselingReplyRepository.deleteById(4L);
    }

    @Test
    public void findAllTest(){
        counselingReplyRepository.findAll().stream().map(CounselingReply::toString).forEach(log::info);
    }

}

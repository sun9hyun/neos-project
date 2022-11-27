package com.app.neos.entity.notice;

import com.app.neos.domain.notice.NoticeDTO;
import com.app.neos.entity.banner.Banner;
import com.app.neos.repository.notice.NoticeRepository;
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
public class NoticeEntityTest {
    @Autowired
    NoticeRepository noticeRepository;

    @Test
    public void saveTest(){
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNoticeTitle("NEOS 이용에 관한 약관입니다.");
        noticeDTO.setNoticeContent("상대방을 비방하는 행동은 하지 않습니다!");

        Notice notice = noticeDTO.toEntity();

        noticeRepository.save(notice);
    }

    @Test
    public void findTest(){
        noticeRepository.findAll().stream().map(Notice::toString).forEach(log::info);
    }


    @Test
    public void deleteTest(){
        noticeRepository.deleteById(1L);
    }
}

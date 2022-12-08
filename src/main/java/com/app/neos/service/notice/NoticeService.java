package com.app.neos.service.notice;

import com.app.neos.domain.notice.NoticeDTO;
import com.app.neos.repository.notice.NoticeCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeCustomRepository noticeCustomRepository;

//    공지사항 목록
    public List<NoticeDTO> findNoticeAll(){
        return noticeCustomRepository.findAll();
    }

//    페이징
    public Page<NoticeDTO> findNoticePage(Pageable pageable){
        return noticeCustomRepository.findAllPage(pageable);
    }

//    공지사항 아이디로 가져오기
    public NoticeDTO findByNoticeId(Long noticeId){
        return noticeCustomRepository.findByNoticeId(noticeId);
    }

}

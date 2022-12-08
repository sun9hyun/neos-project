package com.app.neos.repository.notice;

import com.app.neos.domain.notice.NoticeDTO;
import com.app.neos.domain.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeCustomRepository {

    public List<NoticeDTO> findAll();

    public Page<NoticeDTO> findAllPage(Pageable pageable);

    public NoticeDTO findByNoticeId(Long noticeId);


}

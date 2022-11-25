package com.app.neosproject.repository.notice;

import com.app.neosproject.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}

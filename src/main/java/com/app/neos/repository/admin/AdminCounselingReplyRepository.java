package com.app.neos.repository.admin;

import com.app.neos.entity.counseling.CounselingReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminCounselingReplyRepository extends JpaRepository<CounselingReply, Long> {
    public List<CounselingReply> findByUser_UserId(Long userId);
}

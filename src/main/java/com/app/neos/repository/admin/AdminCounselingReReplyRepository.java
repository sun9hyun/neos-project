package com.app.neos.repository.admin;

import com.app.neos.entity.counseling.CounselingReReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminCounselingReReplyRepository extends JpaRepository<CounselingReReply, Long> {
    public List<CounselingReReply> findByUser_UserId(Long userId);
}

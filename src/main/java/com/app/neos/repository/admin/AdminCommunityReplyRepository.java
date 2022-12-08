package com.app.neos.repository.admin;

import com.app.neos.entity.community.CommunityReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminCommunityReplyRepository extends JpaRepository<CommunityReply, Long> {
    public List<CommunityReply> findByUser_UserId(Long userId);
}

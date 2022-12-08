package com.app.neos.repository.admin;

import com.app.neos.entity.community.CommunityReReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminCommunityReReplyRepository extends JpaRepository<CommunityReReply, Long> {
    public List<CommunityReReply> findByUser_UserId(Long userId);
}

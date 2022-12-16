package com.app.neos.repository.admin;

import com.app.neos.entity.community.CommunityReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminCommunityReplyRepository extends JpaRepository<CommunityReply, Long> {
//    한 유저가 쓴 모든 커뮤니티 댓글 가져오기
    public List<CommunityReply> findByUser_UserId(Long userId);
}

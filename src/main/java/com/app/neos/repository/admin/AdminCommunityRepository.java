package com.app.neos.repository.admin;

import com.app.neos.entity.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminCommunityRepository extends JpaRepository<Community, Long> {
//    한 유저가 쓴 모든 자유 게시글 가져오기
    public List<Community> findByUser_UserId(Long userId);
}

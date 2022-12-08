package com.app.neos.repository.admin;

import com.app.neos.entity.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminCommunityRepository extends JpaRepository<Community, Long> {
    public List<Community> findByUser_UserId(Long userId);
}

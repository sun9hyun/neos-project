package com.app.neosproject.repository.community;

import com.app.neosproject.entity.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}

package com.app.neos.repository.myPage;

import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.study.StudyFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageFollowRepository extends JpaRepository<Follow, Long> {

}

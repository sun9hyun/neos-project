package com.app.neos.repository.myPage;

import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.study.StudyFollow;

import java.util.List;

public interface MyPageFollowCustomRepository {

    public List<Follow> findByUserId(Long userId);

}

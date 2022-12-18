package com.app.neos.repository.myPage;

import com.app.neos.entity.user.User;

public interface MyPageUserCustomRepository {

    // 마이페이지 유저정보 수정
    public void update(User user);

}

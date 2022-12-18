package com.app.neos.repository.myPage;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;

public interface MyPageCollegeCustomRepository {

    // 마이페이지 유저정보 수정
    public CollegeDTO findCollege(String college);

}

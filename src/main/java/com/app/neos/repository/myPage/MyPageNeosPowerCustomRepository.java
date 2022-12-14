package com.app.neos.repository.myPage;

import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.entity.user.User;

import java.util.List;

public interface MyPageNeosPowerCustomRepository {

    // 네오력 유저아이디로 조회
    public List<NeosPowerDTO> neosPowerList(Long userId);


}

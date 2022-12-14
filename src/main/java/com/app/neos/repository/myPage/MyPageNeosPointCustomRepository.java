package com.app.neos.repository.myPage;

import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.domain.neos.NeosPowerDTO;

import java.util.List;

public interface MyPageNeosPointCustomRepository {

    // 네오포인트 유저아이디로 조회
    public List<NeosPointDTO> neosPointList(Long userId);


}

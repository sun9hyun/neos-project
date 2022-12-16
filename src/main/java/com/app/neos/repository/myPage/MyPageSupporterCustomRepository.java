package com.app.neos.repository.myPage;

import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.domain.study.StudySupporterDTO;

import java.util.List;

public interface MyPageSupporterCustomRepository {

    public List<StudySupporterDTO> findByUserId(Long userId);


}

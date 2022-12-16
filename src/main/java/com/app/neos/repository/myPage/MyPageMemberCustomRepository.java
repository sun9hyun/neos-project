package com.app.neos.repository.myPage;

import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.domain.study.StudyMemberDTO;
import com.app.neos.entity.study.StudyMember;

import java.util.List;

public interface MyPageMemberCustomRepository {

    public List<StudyMemberDTO> findByUserId(Long userId);


}

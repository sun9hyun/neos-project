package com.app.neos.repository.admin;

import com.app.neos.entity.study.StudyFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminStudyFollowRepository extends JpaRepository<StudyFollow, Long> {
//    해당 스터디의 좋아요 수 가져오기
    public int countByStudyStudyId(Long studyId);

}

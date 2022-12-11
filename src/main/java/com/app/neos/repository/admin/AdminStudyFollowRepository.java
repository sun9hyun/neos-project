package com.app.neos.repository.admin;

import com.app.neos.entity.study.StudyFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminStudyFollowRepository extends JpaRepository<StudyFollow, Long> {
    public int countByStudyStudyId(Long studyId);

}

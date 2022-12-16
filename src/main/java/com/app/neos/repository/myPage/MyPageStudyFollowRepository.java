package com.app.neos.repository.myPage;

import com.app.neos.entity.study.Study;
import com.app.neos.entity.study.StudyFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPageStudyFollowRepository extends JpaRepository<StudyFollow, Long> {

}

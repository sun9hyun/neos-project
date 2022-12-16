package com.app.neos.repository.myPage;

import com.app.neos.entity.study.StudyMember;
import com.app.neos.entity.study.StudySupporter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageSupporterRepository extends JpaRepository<StudySupporter, Long> {
}

package com.app.neos.repository.myPage;

import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.study.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageMemberRepository extends JpaRepository<StudyMember, Long> {
}

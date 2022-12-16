package com.app.neos.repository.admin;

import com.app.neos.entity.study.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminStudyRepository extends JpaRepository<Study, Long> {
//    한 유저가 만든 모든 스터디 가져오기
    public List<Study> findByUser_UserId(Long userId);
}

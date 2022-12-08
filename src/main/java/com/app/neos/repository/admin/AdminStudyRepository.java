package com.app.neos.repository.admin;

import com.app.neos.entity.study.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminStudyRepository extends JpaRepository<Study, Long> {
    public List<Study> findByUser_UserId(Long userId);
}

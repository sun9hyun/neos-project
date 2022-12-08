package com.app.neos.repository.admin;

import com.app.neos.entity.counseling.Counseling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminCounselingRepository extends JpaRepository<Counseling, Long> {
    public List<Counseling> findByUser_UserId(Long userId);
}

package com.app.neos.repository.admin;

import com.app.neos.entity.counseling.Counseling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminCounselingRepository extends JpaRepository<Counseling, Long> {
//    한 유저가 작성한 모든 고민 게시판 가져오기
    public List<Counseling> findByUser_UserId(Long userId);
}

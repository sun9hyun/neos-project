package com.app.neos.repository.myPage;

import com.app.neos.entity.college.College;
import com.app.neos.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageCollegeRepository extends JpaRepository<College, Long> {
}

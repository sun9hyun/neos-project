package com.app.neos.repository.college;

import com.app.neos.entity.college.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College,Long> {
}

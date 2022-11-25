package com.app.neosproject.repository.college;

import com.app.neosproject.entity.college.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College,Long> {
}

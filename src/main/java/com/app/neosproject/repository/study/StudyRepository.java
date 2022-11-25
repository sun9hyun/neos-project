package com.app.neosproject.repository.study;

import com.app.neosproject.entity.study.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}

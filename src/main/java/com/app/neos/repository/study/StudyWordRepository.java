package com.app.neos.repository.study;

import com.app.neos.entity.study.StudyWork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyWordRepository extends JpaRepository<StudyWork, Long> {
}

package com.app.neos.repository.study;

import com.app.neos.entity.study.StudyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyQuestionRepository extends JpaRepository<StudyQuestion, Long> {

}

package com.app.neos.repository.admin;

import com.app.neos.entity.study.StudyFeedReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminStudyFeedReplyRepository extends JpaRepository<StudyFeedReply, Long> {

    public List<StudyFeedReply> findAllByStudyFeedReplyWriter_UserId(Long userId);

}

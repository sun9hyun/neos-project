package com.app.neos.repository.admin;

import com.app.neos.entity.study.StudyFeedReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminStudyFeedReplyRepository extends JpaRepository<StudyFeedReply, Long> {
// 한 유저가 쓴 모든 피드 댓글 가져오기
    public List<StudyFeedReply> findAllByStudyFeedReplyWriter_UserId(Long userId);

}

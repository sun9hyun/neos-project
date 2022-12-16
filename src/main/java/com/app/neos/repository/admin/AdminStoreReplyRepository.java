package com.app.neos.repository.admin;

import com.app.neos.entity.store.StoreReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminStoreReplyRepository extends JpaRepository<StoreReply, Long> {
//    한 유저가 쓴 모든 자료 상점 댓글 가져오기
    public List<StoreReply> findByUser_UserId(Long userId);
}

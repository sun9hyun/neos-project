package com.app.neos.repository.admin;

import com.app.neos.entity.store.StoreReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminStoreReplyRepository extends JpaRepository<StoreReply, Long> {
    public List<StoreReply> findByUser_UserId(Long userId);
}

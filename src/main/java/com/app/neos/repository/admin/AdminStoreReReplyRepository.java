package com.app.neos.repository.admin;

import com.app.neos.entity.store.StoreReReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminStoreReReplyRepository extends JpaRepository<StoreReReply, Long> {
    public List<StoreReReply> findByUser_UserId(Long userId);
}

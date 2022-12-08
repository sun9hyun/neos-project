package com.app.neos.repository.admin;

import com.app.neos.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminStoreRepository extends JpaRepository<Store, Long> {
    public List<Store> findByUser_UserId(Long userId);
}

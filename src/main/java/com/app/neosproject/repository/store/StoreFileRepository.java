package com.app.neosproject.repository.store;

import com.app.neosproject.entity.store.StoreFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreFileRepository extends JpaRepository<StoreFile, Long> {
}

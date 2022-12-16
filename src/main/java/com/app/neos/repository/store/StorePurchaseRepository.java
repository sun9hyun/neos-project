package com.app.neos.repository.store;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.store.StorePurchase;
import com.app.neos.entity.study.StudySupporter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorePurchaseRepository extends JpaRepository<StorePurchase, Long> {


}

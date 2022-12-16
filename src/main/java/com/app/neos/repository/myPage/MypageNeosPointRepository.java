package com.app.neos.repository.myPage;

import com.app.neos.entity.neos.NeosPoint;
import com.app.neos.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageNeosPointRepository extends JpaRepository<NeosPoint, Long> {
}

package com.app.neos.repository.myPage;

import com.app.neos.entity.store.Store;
import com.app.neos.entity.study.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageStudyRepository extends JpaRepository<Study, Long> {


}

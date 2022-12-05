package com.app.neos.repository.study;

import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyCustomRepository {

    public Page<Study> findAllPage(Pageable pageable);

    public int getTotal();
}

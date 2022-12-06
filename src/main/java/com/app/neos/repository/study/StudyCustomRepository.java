package com.app.neos.repository.study;

import com.app.neos.domain.study.StudySearch;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface StudyCustomRepository {

    public Page<Study> findAllPage(Pageable pageable, StudySearch search);
    public List<Study> findUntilFour();
    public long pageTotal(Pageable pageable, StudySearch search);
    public int getTotal();
    public Slice<Study> findAllSlice(Pageable pageable);
}

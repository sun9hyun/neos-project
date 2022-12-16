package com.app.neos.repository.search;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.study.StudyDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface SearchCustomRepository {
    public Slice<StudyDTO> findByKeywordStudy(String keyword , Pageable pageable);

    public Slice<StoreDTO> findByKeywordStore(String keyword , Pageable pageable);

    public Slice<StudyDTO> findByStudyList(Pageable pageable);
}

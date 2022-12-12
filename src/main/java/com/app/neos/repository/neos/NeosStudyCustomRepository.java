package com.app.neos.repository.neos;

import com.app.neos.domain.study.StudyDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface NeosStudyCustomRepository {

    public Slice<StudyDTO> findStudyAllPage (Pageable pageable);
}

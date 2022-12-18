package com.app.neos.repository.neos;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.entity.study.Study;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface NeosStudyCustomRepository {


    public List<Study> findStudyForMain();
}

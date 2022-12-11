package com.app.neos.repository.counseling;

import com.app.neos.domain.counseling.CounselingDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CounselingCustomRepository {
    public List<CounselingDTO> findAll();

    public Slice<CounselingDTO> findAllPage(Pageable pageable);
}

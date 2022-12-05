package com.app.neos.repository.counseling;

import com.app.neos.domain.counseling.CounselingDTO;

import java.util.List;

public interface CounselingCustomRepository {
    public List<CounselingDTO> findAll();
}

package com.app.neos.repository.counseling;

import com.app.neos.domain.counseling.CounselingReplyDTO;

import java.util.List;

public interface CounselingReplyCustomRepository {
    public List<CounselingReplyDTO> findAll(Long counselingId);
}

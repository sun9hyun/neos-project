package com.app.neos.repository.community;

import com.app.neos.domain.community.CommunityReplyDTO;

import java.util.List;

public interface CommunityReplyCustomRepository {
    public List<CommunityReplyDTO> findAll(Long communityId);
}

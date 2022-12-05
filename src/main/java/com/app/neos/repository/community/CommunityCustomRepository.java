package com.app.neos.repository.community;

import com.app.neos.domain.community.CommunityDTO;

import java.util.List;

public interface CommunityCustomRepository {
    public List<CommunityDTO> findAll();

    public CommunityDTO findByCommunityId(Long communityId);

}

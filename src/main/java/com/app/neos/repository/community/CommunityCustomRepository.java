package com.app.neos.repository.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.entity.community.Community;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CommunityCustomRepository {
    public List<CommunityDTO> findAll();

    public Slice<CommunityDTO> findAllPage(Pageable pageable);

    public CommunityDTO findByCommunityId(Long communityId);

//    public CommunityDTO update(Long communityId);

}

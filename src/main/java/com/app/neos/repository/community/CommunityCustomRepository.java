package com.app.neos.repository.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.CommunityLikeDTO;
import com.app.neos.entity.community.CommunityLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface CommunityCustomRepository {
    public List<CommunityDTO> findAll();

    public Slice<CommunityDTO> findAllPage(Pageable pageable);

    public CommunityDTO findByCommunityId(Long communityId);

    public List<CommunityLikeDTO> findByLikeId(Long communityId);

    public List<CommunityLikeDTO> findLike(Long communityId, Long userId);

//    public CommunityDTO update(Long communityId);

    public List<CommunityLikeDTO> findByCommunityAndUser(Long communityId, Long userId);

}

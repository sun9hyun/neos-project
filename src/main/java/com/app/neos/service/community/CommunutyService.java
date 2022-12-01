package com.app.neos.service.community;

import com.app.neos.entity.community.Community;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunutyService {
    private  final CommunityRepository communityRepository;
    private  final UserRepository userRepository;

    public void saveCommunity(Community community){
        communityRepository.save(community);
    }

}

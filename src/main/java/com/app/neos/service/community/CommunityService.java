package com.app.neos.service.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.user.User;
import com.app.neos.repository.community.CommunityCustomRepository;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private  final CommunityRepository communityRepository;
    private  final CommunityCustomRepository communityCustomRepository;
    private  final UserRepository userRepository;

    //추가
    public void saveCommunity(CommunityDTO communityDTO){
        Community community = communityDTO.toEntity();
        Long userId = communityDTO.getUserId();
        User user = userRepository.findById(userId).get();
        community.changeUser(user);
        communityRepository.save(community);
    }

    //목록 조회
    public List<CommunityDTO> findAll(){
        return communityCustomRepository.findAll();
    }

    //상세보기
    public CommunityDTO findByCommunityId(Long communityId){
        return communityCustomRepository.findByCommunityId(communityId);
    }

    //추가
    @Transactional
    public void updateCommunity(CommunityDTO communityDTO){
//        Community community = communityDTO.toEntity();
        Community community= communityRepository.findById(communityDTO.getCommunityId()).get();
        community.update(communityDTO);
    }

    //삭제
    public void deleteCommunity(CommunityDTO communityDTO){
        communityRepository.deleteById(communityDTO.getCommunityId());
    }
    
}

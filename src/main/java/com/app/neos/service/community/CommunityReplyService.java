package com.app.neos.service.community;

import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.community.CommunityReply;
import com.app.neos.entity.user.User;
import com.app.neos.repository.community.CommunityReplyCustomRepository;
import com.app.neos.repository.community.CommunityReplyRepository;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityReplyService {
    public final CommunityReplyRepository communityReplyRepository;
    public final CommunityReplyCustomRepository communityReplyCustomRepository;
    public final CommunityRepository communityRepository;
    public final UserRepository userRepository;

    public void saveCommunityReply(CommunityReplyDTO communityReplyDTO){
        CommunityReply communityReply = communityReplyDTO.toEntity();
        Long userId = communityReplyDTO.getUserId();
        User user = userRepository.findById(userId).get();
        Long communityId = communityReplyDTO.getCommunityId();
        Community community = communityRepository.findById(communityId).get();
        communityReply.changeUser(user);
        communityReply.changeCommunity(community);
        communityReplyRepository.save(communityReply);
    }

    public List<CommunityReplyDTO> findReplyAll(Long communityId){
        return communityReplyCustomRepository.findAll(communityId);
    }

    @Transactional
    public void updateReply(CommunityReplyDTO communityReplyDTO){
        CommunityReply communityReply = communityReplyRepository.findById(communityReplyDTO.getCommunityReplyId()).get();
        communityReply.update(communityReplyDTO);
    }

    public void deleteReply(Long communityReplyId){
        communityReplyRepository.deleteById(communityReplyId);
    }

}

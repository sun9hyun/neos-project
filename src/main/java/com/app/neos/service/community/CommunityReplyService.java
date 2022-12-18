package com.app.neos.service.community;

import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.community.CommunityReply;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.user.User;
import com.app.neos.repository.community.CommunityReplyCustomRepository;
import com.app.neos.repository.community.CommunityReplyRepository;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.repository.neos.NeosPowerRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.alarm.AlarmService;
import com.app.neos.type.alarm.AlarmCategory;
import com.app.neos.type.point.NeosPowerContent;
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
    private final NeosPowerRepository neosPowerRepository;
    private final AlarmService alarmService;

    //작성
    public void saveCommunityReply(CommunityReplyDTO communityReplyDTO){
        CommunityReply communityReply = communityReplyDTO.toEntity();
        Long userId = communityReplyDTO.getUserId();
        User user = userRepository.findById(userId).get();
        Long communityId = communityReplyDTO.getCommunityId();
        Community community = communityRepository.findById(communityId).get();
        communityReply.changeUser(user);
        communityReply.changeCommunity(community);
//        communityReplyRepository.save(communityReply);
        CommunityReply communityReply1 =  communityReplyRepository.save(communityReply);
        communityReplyDTO = communityReply1.toDTO();
        AlarmCategory category = AlarmCategory.COMMUNITYREPLY;
        alarmService.alarm(communityReplyDTO,category);

    }

    //목록
    public List<CommunityReplyDTO> findReplyAll(Long communityId){
        return communityReplyCustomRepository.findAll(communityId);
    }

    //수정
    @Transactional
    public void updateReply(CommunityReplyDTO communityReplyDTO){
        CommunityReply communityReply = communityReplyRepository.findById(communityReplyDTO.getCommunityReplyId()).get();
        communityReply.update(communityReplyDTO);
    }

    //삭제
    public void deleteReply(Long communityReplyId){
        communityReplyRepository.deleteById(communityReplyId);
    }

    //네오력 상승
    @Transactional
    public void postEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+10);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(10);
        dto.setNeosPowerContent(NeosPowerContent.REPLY);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }

    //네오력 차감
    @Transactional
    public void postDeleteEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()-10);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(-10);
        dto.setNeosPowerContent(NeosPowerContent.REPLY);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }

}

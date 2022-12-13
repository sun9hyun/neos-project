package com.app.neos.service.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.CommunityLikeDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.community.CommunityLike;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.user.User;
import com.app.neos.repository.community.CommunityCustomRepository;
import com.app.neos.repository.community.CommunityLikeRepository;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.repository.neos.NeosPowerRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.point.NeosPowerContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private  final CommunityRepository communityRepository;
    private  final CommunityCustomRepository communityCustomRepository;
    private  final CommunityLikeRepository communityLikeRepository;
    private  final UserRepository userRepository;
    private final NeosPowerRepository neosPowerRepository;

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

    //수정
    @Transactional
    public void updateCommunity(CommunityDTO communityDTO){
        Community community= communityRepository.findById(communityDTO.getCommunityId()).get();
        community.update(communityDTO);
    }

    //삭제
    public void deleteCommunity(CommunityDTO communityDTO){
        communityRepository.deleteById(communityDTO.getCommunityId());
    }

    //네오력 상승
    @Transactional
    public void postEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+50);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(50);
        dto.setNeosPowerContent(NeosPowerContent.POST);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }

    //네오력 차감
    @Transactional
    public void postDeleteEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()-50);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(-50);
        dto.setNeosPowerContent(NeosPowerContent.POST);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }






    //--------------------------------------------------------------------------------------------------------
    //좋아요 수 수정
    @Transactional
    public void updateCommunityLike(CommunityDTO communityDTO){
        Community community= communityRepository.findById(communityDTO.getCommunityId()).get();
        community.updateCommunityLikeCount(communityDTO);
    }

//    public Optional<CommunityLike> findByLikeId(Long communityId){
//        return communityLikeRepository.findById(communityId);
//    }

//    public List<CommunityLikeDTO> findByLikeId(Long communityId){
//        return communityCustomRepository.findByLikeId(communityId);
//    }
    public List<CommunityLikeDTO> findLike(Long communityId, Long userId){
        return communityCustomRepository.findLike(communityId, userId);
    }

    //사용자가 이미 좋아요 한 게시물인지 체크
    public int LikeCheck(Long communityId, Long userId){
        Optional<CommunityLike> likeCheck = communityLikeRepository.findByCommunityAndUser(communityId, userId);

        if (likeCheck.isEmpty()){
            return 0;
        }else {
            return 1;
        }
    }




    
}

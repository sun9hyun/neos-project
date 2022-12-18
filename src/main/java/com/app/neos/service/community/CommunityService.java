package com.app.neos.service.community;

import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.FollowDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.community.Community;
import com.app.neos.entity.community.CommunityLike;
import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.user.User;
import com.app.neos.repository.community.CommunityCustomRepository;
import com.app.neos.repository.community.CommunityLikeCustomRepository;
import com.app.neos.repository.community.CommunityLikeRepository;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.repository.follow.FollowCustomRepository;
import com.app.neos.repository.neos.NeosPowerRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.point.NeosPowerContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private  final CommunityRepository communityRepository;
    private  final CommunityCustomRepository communityCustomRepository;
    private  final CommunityLikeRepository communityLikeRepository;
    private  final CommunityLikeCustomRepository communityLikeCustomRepository;
    private  final UserRepository userRepository;
    private final NeosPowerRepository neosPowerRepository;
    private final UserCustomRepository userCustomRepository;
    private final FollowCustomRepository followCustomRepository;

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

    //네오스인
    public List<UserDTO> findNeosUser(){
        return communityCustomRepository.findNeosUser();
    }

    //스터디
    public List<StudyDTO> findStudy(){
        return communityCustomRepository.findStudy();
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

    // 유저 상세보기
    public UserDTO findByUserId(Long userId){
        return userCustomRepository.findById(userId);
    }

    //--------------------------------------------------------------------------------------------------------

    //    내가 구독한 사람
    public List<FollowDTO> showMyIdList(Long myId){
        return followCustomRepository.findAllByMyId(myId).stream().map(Follow::toDTO).collect(Collectors.toList());
    }

    //  게시글에 좋아요를 눌렀는지 중복 체크
    @Transactional
    public boolean checkLike(Long userId, Long communityId){
        return communityLikeCustomRepository.duplicate(userId, communityId);
    }

    //    좋아요 하기
    @Transactional
    public boolean communityLike(Long userId, Long communityId){
        if (!communityLikeCustomRepository.duplicate(userId, communityId)){
            CommunityLike communityLike = CommunityLike.create();
            communityLike.changeCommunity(communityRepository.findById(communityId).get());
            communityLike.changeUser(userRepository.findById(userId).get());
            communityLikeRepository.save(communityLike);

            Community community = communityRepository.findById(communityId).get();
            community.updateCommunityLikeCount(community.getCommunityLikeCount()+1);
        }
        return false;
    }

    //--------------------------------------------------------------------------------------------------------

    //좋아요 수 수정
    @Transactional
    public void updateCommunityLike(CommunityDTO communityDTO){
        Community community= communityRepository.findById(communityDTO.getCommunityId()).get();
        community.updateCommunityLikeCount(communityDTO.getCommunityLikeCount()+1);
    }

//    public Optional<CommunityLike> findByLikeId(Long communityId){
//        return communityLikeRepository.findById(communityId);
//    }

//    public List<CommunityLikeDTO> findByLikeId(Long communityId){
//        return communityCustomRepository.findByLikeId(communityId);
//    }
//    public List<CommunityLikeDTO> findLike(Long communityId, Long userId){
//        return communityCustomRepository.findLike(communityId, userId);
//    }

    //사용자가 이미 좋아요 한 게시물인지 체크
//    public int LikeCheck(Long communityId, Long userId){
//        Optional<CommunityLike> likeCheck = communityLikeRepository.findByCommunityAndUser(communityId, userId);
//
//        if (likeCheck.isEmpty()){
//            return 0;
//        }else {
//            return 1;
//        }
//    }







    
}

package com.app.neos.service.neosUser;

import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.FollowDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import com.app.neos.repository.follow.FollowCustomRepository;
import com.app.neos.repository.follow.FollowRepository;
import com.app.neos.repository.neos.NeosPowerRepository;
import com.app.neos.repository.neos.NeosUserCustomRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.point.NeosPowerContent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NeosUserService {

    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;
    private final NeosUserCustomRepository neosUserCustomRepository;
    private final NeosPowerRepository neosPowerRepository;
    private final FollowCustomRepository followCustomRepository;

    // 유저 추가
    public void saveUser(User user){
        userRepository.save(user);
    }


    // 유저 목록
    public List<UserDTO> findUser() {
        return neosUserCustomRepository.findAll() ;
    }


    // 유저 상세보기
    public UserDTO findByUserId(Long userId){
        return userCustomRepository.findById(userId);
    }

//    유저가 속한 스터디
    public List<Study> findByStudyId(Long studyId){
        return neosUserCustomRepository.findByUserId(studyId);
    }


//  유저 검색
    public Slice<UserDTO> findBykeyword(String keyword, Pageable pageable){
        return neosUserCustomRepository.findByKeywordSlice(keyword,pageable);
    }



//    네오스 적립 , 차감
    @Transactional
    public void postEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+100);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(100);
        dto.setNeosPowerContent(NeosPowerContent.follow);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }
    @Transactional
    public void postDeleteEXP(Long userId){
        User user = userRepository.findById(userId).get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()-100);
        user.levelUp();

        NeosPowerDTO dto = new NeosPowerDTO();
        dto.setNeosPowerAbility(-100);
        dto.setNeosPowerContent(NeosPowerContent.follow);
        NeosPower entity = dto.toEntity();
        entity.changeUser(user);
        neosPowerRepository.save(entity);
    }


//    내가 구독한 사람
    public List<FollowDTO> showMyIdList(Long myId){
        return followCustomRepository.findAllByMyId(myId).stream().map(Follow::toDTO).collect(Collectors.toList());
    }

//    나를 구독한 사람
    public List<FollowDTO> showFollowIdList(Long followingId){
        return followCustomRepository.findByFollowingId(followingId).stream().map(Follow::toDTO).collect(Collectors.toList());
    }



}

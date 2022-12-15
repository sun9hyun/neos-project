package com.app.neos.service.neosUser;

import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class NeosUserService {

    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;
    private final NeosUserCustomRepository neosUserCustomRepository;
    private final NeosPowerRepository neosPowerRepository;


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

//    유저 검색

    /*@Transactional
    public List<UserDTO> findByKeyword(String keyword){
        List<UserDTO> userDTOList =neosUserCustomRepository.findByKeyword(keyword);

        return userDTOList;
    }*/

    public Slice<UserDTO> findBykeyword(String keyword, Pageable pageable){
        return neosUserCustomRepository.findByKeywordSlice(keyword,pageable);
    }

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





}

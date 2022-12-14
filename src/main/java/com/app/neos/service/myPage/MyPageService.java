package com.app.neos.service.myPage;

import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.domain.neos.NeosPowerDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.user.User;
import com.app.neos.repository.college.CollegeRepository;
import com.app.neos.repository.myPage.MyPageNeosPointCustomRepository;
import com.app.neos.repository.myPage.MyPageNeosPowerCustomRepository;
import com.app.neos.repository.myPage.MypageNeosPowerRepository;
import com.app.neos.repository.store.StoreCustomRepository;
import com.app.neos.repository.store.StoreReplyCustomRepository;
import com.app.neos.repository.store.StoreReplyRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;
    private final StoreCustomRepository storeCustomRepository;
    private final StudyCustomRepository studyCustomRepository;
    private final CollegeRepository collegeRepository;
    private final MyPageNeosPowerCustomRepository myPageNeosPowerCustomRepository;
    private final MypageNeosPowerRepository myPageNeosPowerRepository;
    private final MyPageNeosPointCustomRepository myPageNeosPointCustomRepository;

    // 마이페이지 유저 업데이트
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDTO userDTO){

        updateEntity(userDTO);
        User user = userRepository.findById(userDTO.getUserId()).get();
        College college = collegeRepository.findById(userDTO.getCollegeId()).get();
        user.changeCollege(college);

    }

    @Transactional
    public void updateEntity(UserDTO userDTO){
        User user = userRepository.findById(userDTO.getUserId()).get();
        user.update(userDTO);
    }

    // 마이페이지 네오력 조회
    public List<NeosPowerDTO> findNeosPower(Long userId) {
        return myPageNeosPowerCustomRepository.neosPowerList(userId);
    }

    // 마이페이지 네오포인트 조회
    public List<NeosPointDTO> findNeosPoint(Long userId){
        return myPageNeosPointCustomRepository.neosPointList(userId);
    }
    
//    // 마이페이지 유저 정보 전달(대학교제외)
//    public User findUserById(Long userId){
//        return userRepository.findById(userId).get();
//    }
//
//    // 마이페이지 유저 정보 전달
//    public UserDTO findUserById2(Long userId){
//        return userCustomRepository.findById(userId);
//    }

}

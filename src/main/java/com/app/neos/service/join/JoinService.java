package com.app.neos.service.join;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.entity.user.User;
import com.app.neos.repository.college.CollegeRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final CollegeRepository collegeRepository;

    public void join(UserDTO userDTO){
        UserDTO joinUser = userDTO;
        joinUser.setUserNeosBadge("/images/fix/neosLevel1.png");
        joinUser.setUserNeosPowerLevel(1);
        joinUser.setUserNeosPowerAbility(50);

        joinUser.setUserNeosPoint(0);
        joinUser.setUserChattingPoint(1000);
        joinUser.setUserIntroduce("안녕하세요 네오스인입니다.");


        User user = userDTO.toEntity();
        if(joinUser.getCollegeId() != null){
           College college =  collegeRepository.findById(userDTO.getCollegeId()).get();
            user.changeCollege(college);
        }

        userRepository.save(user);
    }

    public boolean duplicateId(String userOauthId){
        return userRepository.findAllByUserOAuthId(userOauthId).size() != 0;
    }

    @Transactional
    public void certify(String token){
        User user = userRepository.findByUserOAuthId(token).get();
        user.certifyOk("true");
    }

}

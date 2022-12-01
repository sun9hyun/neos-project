package com.app.neos.service.neosUser;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NeosUserService {

    private final UserRepository userRepository;

    // 유저 추가
    public void saveUser(User user){
        userRepository.save(user);
    }

    // 유저 목록
    public List<User> findUser() {
        return userRepository.findAll();
    }

}

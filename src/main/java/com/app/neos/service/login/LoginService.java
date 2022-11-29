package com.app.neos.service.login;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;

    public UserDTO login(String readId){
        if(loginOk(readId)){
            return userCustomRepository.findByOauthId(readId);
        }
        return null;
    }

    public boolean loginOk(String realId){
        return userCustomRepository.findByOauthId(realId) != null;
    }


}

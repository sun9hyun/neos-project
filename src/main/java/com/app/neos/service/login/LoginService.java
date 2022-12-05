package com.app.neos.service.login;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;

    @Transactional
    public UserDTO login(String readId){
        if(loginOk(readId)){
            User user = userRepository.findByUserOAuthId(readId).get();
            user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+10);
            user.levelUp();
            return userRepository.findByUserOAuthId(readId).get().toDTO();
        }
        return null;
    }

    public boolean loginOk(String realId){
        return userRepository.findByUserOAuthId(realId).isPresent();
    }




}

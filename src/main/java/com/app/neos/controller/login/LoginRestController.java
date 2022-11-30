package com.app.neos.controller.login;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/*")
public class LoginRestController {
    private final UserCustomRepository userCustomRepository;
    private final UserRepository userRepository;

    @GetMapping("{bno}")
    public UserDTO userInfo(@PathVariable("bno") Long userId){
         User user = userRepository.findById(userId).get();
         if(user.getCollege()==null){
            return userCustomRepository.findNoCollegeById(userId);
         }
         return userCustomRepository.findById(userId);
    }
}

package com.app.neos.controller.login;

import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudySearch;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.study.Study;
import com.app.neos.entity.user.User;
import com.app.neos.repository.study.StudyCustomRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/*")
public class LoginRestController {
    private final UserCustomRepository userCustomRepository;
    private final UserRepository userRepository;
    private final StudyService studyService;
    private final StudyCustomRepository studyCustomRepository;


    @GetMapping("{bno}")
    public UserDTO userInfo(@PathVariable("bno") Long userId){
         User user = userRepository.findById(userId).get();
         if(user.getCollege()==null){
            return userCustomRepository.findNoCollegeById(userId);
         }
         return userCustomRepository.findById(userId);
    }

    @PutMapping("/test")
    public int test(){
        Pageable pageable = PageRequest.of(0,16);
        StudySearch studySearch = new StudySearch();
        return studyCustomRepository.findAllPage(pageable,studySearch).getTotalPages();
    }

}

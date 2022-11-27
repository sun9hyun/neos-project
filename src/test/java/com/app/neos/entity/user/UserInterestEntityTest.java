package com.app.neos.entity.user;

import com.app.neos.domain.user.UserInterestDTO;
import com.app.neos.repository.user.UserInterestRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class UserInterestEntityTest {
    @Autowired
    UserInterestRepository userInterestRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void saveTest(){
        User user = userRepository.findById(3l).get();
        UserInterestDTO userInterestDTO = new UserInterestDTO();
        userInterestDTO.setInterestField("o2o");
        userInterestDTO.setUser(user);
        UserInterest userInterest = userInterestDTO.toEntity();
        userInterest.changeUser(user);
        userInterestRepository.save(userInterest);
    }

    @Test
    public void updateTest(){
        UserInterest userInterest = userInterestRepository.findById(4l).get();
        UserInterestDTO userInterestDTO = new UserInterestDTO();
        userInterestDTO.setInterestField("기타");
        userInterest.update(userInterestDTO);
    }

    @Test
    public void findAllTest(){
        userInterestRepository.findAll().stream().map(UserInterest::toString).forEach(log::info);
    }

    @Test
    public void deleteTest(){
        userInterestRepository.deleteAll();
    }
}

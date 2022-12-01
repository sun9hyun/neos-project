package com.app.neos.service.join;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.entity.user.User;
import com.app.neos.repository.college.CollegeRepository;
import com.app.neos.repository.user.CollegesCustomRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.service.login.LoginService;
import com.app.neos.type.user.UserCollegeMajor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class JoinServiceTest {

    @Autowired
    UserCustomRepository userCustomRepository;
    @Autowired
    LoginService loginService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CollegeRepository collegeRepository;
    @Autowired
    CollegesCustomRepository collegesCustomRepository;

    @Autowired
    JoinService joinService;

    @Test
    public void findAllByOauthIdTest(){
        assertThat(userRepository.findAllByUserOAuthId("2546432919k").size()).isEqualTo(1);
    }

    @Test
    public void findByOauthIdTest(){
        log.info("들어옴"+userRepository.findByUserOAuthId("2546432919k").isPresent());
    }

    @Test
    public void findByOauthIdTest2(){
        log.info(userCustomRepository.findByOauthId("2546432919k").toString());
    }

    @Test
    public void loginTest(){
        log.info(loginService.login("2546432919k").toString());
    }

    @Test
    public void loginDeniedTest(){
        assertThat(loginService.login("sd")).isEqualTo(null);
    }

    @Test
    public void updateTest2(){
        userRepository.findByUserOAuthId("2546432919k").get().updateNeosPower(50);
    }

    @Test
    public void updateTest3(){
        User user = userRepository.findByUserOAuthId("2546432919k").get();
        user.updateNeosPower(user.getUserNeosPower().getUserNeosPowerAbility()+10);
        log.info(userCustomRepository.findByOauthId("2546432919k").toString());
    }

    @Test
    public void findByIdTest2(){
        log.info(userCustomRepository.findById(4l).toString());
    }

    @Test
    public void findAllCollegeTest(){
        collegesCustomRepository.findAll().stream().map(CollegeDTO::toString).forEach(log::info);
    }

    @Test
    public void setCollegesLocation(){
        List<CollegeDTO> collegeDTOList = collegesCustomRepository.findAll();
        List<String> locations = collegeDTOList.stream().map(CollegeDTO::getCollegeCity).collect(Collectors.toList());

        HashSet<String> location = new HashSet<>(locations);
//        assertThat(locations.size()).isEqualTo(6);
//        Assertions.assertThat(location.size()).isEqualTo(5);

        List<String> locationFinal = location.stream().collect(Collectors.toList());

        locationFinal.sort(Comparator.naturalOrder());

        locationFinal.stream().forEach(log::info);

    }

    @Test
    public void findByCollegeCityTest(){
        collegesCustomRepository.findByCollegeCity("서울특별시").stream().map(CollegeDTO::toString).forEach(log::info);
    }

    @Test
    public void equalTest(){
        Long id = userRepository.findById(22l).get().getCollege().getCollegeId();
        log.info(collegeRepository.findById(id).get().toString());
    }

    @Test
    public void findByDTO(){
        log.info(userCustomRepository.findById(22l).toString());
    }

    @Test
    public void findTest(){
        log.info(userCustomRepository.findNoCollegeById(30l).toString());
    }
}

package com.app.neos.entity.user;

import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.college.College;
import com.app.neos.repository.college.CollegeRepository;
import com.app.neos.repository.user.UserInterestRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.user.UserCollegeMajor;
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
public class UserEntityTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserInterestRepository userInterestRepository;
    @Autowired
    CollegeRepository collegeRepository;

    @Test
    public void collegeSave(){
        College college = College.builder().collegeCity("서울").collegeLogoFile("1").collegeEmailDomain("1").collegeName("서울대학교").build();
        collegeRepository.save(college);
    }


    @Test
    public void saveTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserNickName("박종우");
        userDTO.setUserOAuthId("22222");
        userDTO.setUserOAuthEmail("oauthemail@emailasd.com");
        userDTO.setUserCollegeMajor(UserCollegeMajor.MATH);
        userDTO.setUserCollegeYear(4);
        userDTO.setUserCollegeEmail("email@eamil.com");
        userDTO.setUserCollegeCertify(true);
        userDTO.setUserNeosPowerLevel(1);
        userDTO.setUserNeosPowerAbility(0);
        userDTO.setUserNeosBadge("1");
        userDTO.setUserNeosPoint(0);
        userDTO.setUserChattingPoint(1000);
        userDTO.setUserIntroduce("설정전");
        userDTO.setUserFile("기본");


        userDTO.setCollege(collegeRepository.findById(1l).get());
        User user1 = userDTO.toEntity();
        user1.changeCollege(userDTO.getCollege());

        userRepository.save(user1);

    }

    @Test
    public void updateUserTest(){
        User user = userRepository.findById(2l).get();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserNickName("박종dd우");
        userDTO.setUserOAuthId("22222");
        userDTO.setUserOAuthEmail("oauthemail@emailasd.com");
        userDTO.setUserCollegeMajor(UserCollegeMajor.MATH);
        userDTO.setUserCollegeYear(4);
        userDTO.setUserCollegeEmail("email@eamil.com");
        userDTO.setUserCollegeCertify(true);
        userDTO.setUserNeosPowerLevel(1);
        userDTO.setUserNeosPowerAbility(0);
        userDTO.setUserNeosBadge("1");
        userDTO.setUserNeosPoint(0);
        userDTO.setUserChattingPoint(1000);
        userDTO.setUserIntroduce("설정전");
        userDTO.setUserFile("기본");
        user.update(userDTO);
    }

    @Test
    public void deleteUserTest(){
        userRepository.deleteById(2l);
    }


}
